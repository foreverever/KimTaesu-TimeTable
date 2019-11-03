var map = new Map();
map.set("월","mon-line");
map.set("화","tue-line");
map.set("수","web-line");
map.set("목","thu-line");
map.set("금","fri-line");
var lectureId;
var lectureIdInMemo;

$('.card-lecture').click(popupLecture);
$('.lecture-time > a').click(popupLectureTask);
$(document).on('click', '#btn-primary', registerLecture);
$(document).on('click', '#btn-save-memo', addMemo);
$(document).on('click', '#memo-delete', deleteMemo);
$(document).on('click', '.btn-danger', deleteLecture);


function popupLecture(e) {
  e.preventDefault();
  console.log("클릭!!");
  var json = new Object();
  var url = $(this).children('a').attr("href");

  $.ajax({
      type : 'get',
      url : url,
      dataType : 'json',
      error : onError,
      success : function(data, status, jqXHR) {
        var template = $('#modal-lecture-info-script').html();
        var completedTemplate = template.format(data.name, data.formattedStartTime, data.formattedEndTime, data.dates, data.code, data.professor, data.location, data.id);
        $(completedTemplate).modal('show');
        lectureId=data.id;
    }
  })
}

function registerLecture(e) {
  e.preventDefault();
  console.log(lectureId);
  console.log("강의 등록 클릭!");
  var code = $('#popup-code-'+lectureId).text();
  console.log(code);
  var url = "/api/lectures/code/"+code;
  console.log(url);

  $.ajax({
      type : 'get',
      url : url,
      data : code,
      dataType : 'json',
      error : onError,
      success : function(data, status, jqXHR) {
         console.log(data);
         if(data.message) {
            alert(data.message);
            return;
         }
         var startHour = data.formattedStartTime.substr(0,2);
         var time = data.formattedEndTime.substr(0,2) - startHour;
         var lectureNum = data.code.substr(7);
         console.log(time);
         var template = $('#lecture-timeline-script').html();

         if(data.dates.length>=2) {
            var first = map.get(data.dates[0]);
            var second = map.get(data.dates[1]);
            if(time >= 2) {
                 var completedTemplate = template.format("two-hr", startHour, lectureNum, data.name, data.location, data.memos, data.id);
            }
            else var completedTemplate = template.format("", startHour, lectureNum, data.name, data.location, data.memos, data.id);
            $('#' + first).append(completedTemplate);
            $('#' + second).append(completedTemplate);
         }
         else {
            var first = map.get(data.dates[0]);
            if(time >= 2) {
                var completedTemplate = template.format("two-hr", startHour, lectureNum, data.name, data.location, data.memos, data.id);
            }
            else var completedTemplate = template.format("", startHour, lectureNum, data.name, data.location, data.memos, data.id);
            $('#' + first).append(completedTemplate);
         }
            $('.lecture-time > a').click(popupLectureTask);
            location.reload();
      }
  })
}

function goSearch(e){
  e.preventDefault();
  console.log("검색 클릭!");
  var search = $('#search-contents').val();
  console.log(search);
  var url = $('.form-search').attr('action');

  $.ajax({
      type : 'get',
      url : url,
      data : {search : search},
      dataType : 'json',
      error : onError,
      success : function(data, status, jqXHR) {
        var viewTemplate = "";
        for(var i = 0; i<data.length; i++){
            console.log(data[i]);
            var template = $('#list-lecture-script').html();
            viewTemplate += template.format(data[i].id,data[i].name,data[i].formattedStartTime,data[i].formattedEndTime,data[i].dates,data[i].code,data[i].professor, data[i].location);
        }
        $(".list-lecture").html(viewTemplate);
        $('.card-lecture').click(popupLecture);
      }
  })
}

function popupLectureTask(e) {
  e.preventDefault();
  console.log("클릭!!");
  var json = new Object();
  var url = $(this).attr("href");
  console.log(url);

  $.ajax({
      type : 'get',
      url : url,
      dataType : 'json',
      error : onError,
      success : function(data, status, jqXHR) {
        var memoList = "";

        //메모 리스트 출력화면 구성
        for(var i = 0; i<data.memos.length; i++){
            var memoTemplate = $('#modal-list-script').html();
            memoList+= memoTemplate.format(data.memos[i].title, data.memos[i].contents, data.id, data.memos[i].id);
        }

        //팝업창에 메모 출력하게 끔
        var template = $('#modal-lecture-task-script').html();
        var completedTemplate = template.format(data.name, data.formattedStartTime, data.formattedEndTime, data.dates, data.code, data.professor, data.location, data.id, memoList);

        $(completedTemplate).modal('show');
        $(document).on('click', '.btn-light-primary', function () {
            lectureIdInMemo = data.id;
            return $('#modal-lecture-memo').modal('show');
        });
        //tooltip 활성 (메모 내용)
        $(document).on('mouseover','.memo-list', function() {
             return $('[data-toggle="tooltip"]').tooltip();
        });
    }
  })
}

function addMemo(e){
    e.preventDefault();
    console.log("메모!!");
    var json = new Object();
    json.title = $('#recipient-name').val();
    json.contents = $('#message-text').val();
    var url = $('#add-memo').attr('action') +  lectureIdInMemo + '/memos';

    $.ajax({
        type : 'post',
        url : url,
        data : JSON.stringify(json),
        contentType : "application/json",
        dataType : 'json',
        error : onError,
        success : function(data, status, jqXHR) {
            if(data.message) alert(data.message);
            else location.reload();
        }
    })

}

function deleteMemo(e) {
    e.preventDefault();
    console.log("메모 삭제 버튼 클릭!");

    var deleteBtn = $(this);
    var url = deleteBtn.parent('a').attr('href');
    console.log(url);

    var deleteConfirm = confirm("해당 메모를 삭제하시겠습니까?");
    if(deleteConfirm) {
        $.ajax({
                type : 'delete',
                url : url,
                error : onError,
                success : function(data, status, jqXHR) {
                    deleteBtn.closest('li').remove();
                }
            })
    }
}

function deleteLecture(e) {
    e.preventDefault();
    console.log("과목 삭제 버튼 클릭!!");
    var url = $(this).parent().attr('action');
    console.log(url);

    var deleteConfirm = confirm("해당 과목을 삭제하시겠습니까?");
    if(deleteConfirm) {
        $.ajax({
                type : 'delete',
                url : url,
                error : onError,
                success : function(data, status, jqXHR) {
                    alert("해당 과목이 삭제되었습니다.");
                    location.reload();
                }
            })
    }
}

$(function () {
  $('[data-toggle="tooltip"]').tooltip();
});

//$(function () {
//  $('[data-toggle="popover"]').popover({
//    container: 'body',
//    html: true,
//    placement: 'right',
//    sanitize: false,
//    content: function () {
//    return $("#PopoverContent").html();
//    }
//  });
//});


//에러 메세지
function onError(jqXHR, status, errorThrown) {
    console.log(jqXHR.responseText);    //json값 다 보여줌 (키,밸류 모두다)
    console.log(jqXHR);
    alert(jqXHR.responseJSON.message);  //예외처리에서 받은 리턴값(responseEntity<ErrorMessage>)의 json객체의 키(message)값의 value를 가져온다.
}

//템플릿 추가
String.prototype.format = function() {
  var args = arguments;
       return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
         ? args[number]
             : match
        ;
  });
};
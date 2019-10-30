$('.card-lecture').click(popupLecture);

var lectureId;
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

$(document).on('click', '#btn-primary', registerLecture);

var map = new Map();
map.set("월","mon-line");
map.set("화","tue-line");
map.set("수","web-line");
map.set("목","thu-line");
map.set("금","fri-line");

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
         var startHour = data.formattedStartTime.substr(0,2);
         var time = data.formattedEndTime.substr(0,2) - startHour;
         var lectureNum = data.code.substr(7);
         console.log(time);
         var template = $('#lecture-timeline-script').html();

         if(data.dates.length>=2) {
            var first = map.get(data.dates[0]);
            var second = map.get(data.dates[1]);
            var completedTemplate = template.format("two-hr", startHour, lectureNum, data.name, data.location, data.memos);

            $('#' + first).append(completedTemplate);
            $('#' + second).append(completedTemplate);
         }
         else {
            var first = map.get(data.dates[0]);
            var completedTemplate = template.format("", startHour, lectureNum, data.name, data.location, data.memos);
            $('#' + first).append(completedTemplate);
         }
            $('.lecture-time > a').click(popupLectureTask);
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

$('.lecture-time > a').click(popupLectureTask);


function popupLectureTask() {
  $('#modal-lecture-task').modal('show');
}

$(function () {
  $('[data-toggle="tooltip"]').tooltip();
});

$(function () {
  $('[data-toggle="popover"]').popover({
    container: 'body',
    html: true,
    placement: 'right',
    sanitize: false,
    content: function () {
    return $("#PopoverContent").html();
    }
  });
});


//에러 메세지
function onError(jqXHR, status, errorThrown) {
    console.log(jqXHR.responseText);    //json값 다 보여줌 (키,밸류 모두다) ex) {"message":"아이디 또는 비밀번호가 다릅니다."}
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
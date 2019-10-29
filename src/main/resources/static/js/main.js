$('.card-lecture').click(function (e) {
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
        var completedTemplate = template.format(data.name, data.formattedStartTime, data.formattedEndTime, data.dates, data.code, data.professor, data.location);
        $(completedTemplate).modal('show');
    }
  })

});

$('.lecture-time > a').click(function () {
  $('#modal-lecture-task').modal('show');
});

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
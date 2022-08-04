function loginCheck() {
	if(document.frm.userid.value.length == 0){
		alert("아이디를 써주세요")
		frm.userid.focus();
		return false;
	}
	if(document.frm.pw.value.length == 0){
		
		alert("암호는 반드시 입력해야합니다.")
		frm.pwd.focus();
		return false;
	}
	
	return true;
};

function idCheck() {
	if(document.frm.userid.value==""){
		alert("아이디를 입력하세요")
		frm.userid.focus();
		return;
	}
	var url = "idCheck.do?userid=" + document.frm.userid.value;
	window.open(url, "_blank_1",
	"toolbar=no, menubar=nom scrollbars=yes, resizable=no, width=450, height=200");

	return true;
}

function idok() {
	//opener은 현재 창을 열어준 부모창을 의미.. 이전 join폼에 값을  현재창의 값으로 저장
	opener.frm.userid.value = document.frm.userid.value;
	opener.frm.reid.value = document.frm.userid.value;
	self.close();
}

function joinCheck() {
	if(document.frm.name.value.length == 0){
		alert("이름을 써주세요");
		frm.name.focus();
		return false;
	}
	if(document.frm.userid.value.length == 0){
		alert("아이디를 입력하세요.");
		frm.userid.focus();
		return false;
	}
	if(document.frm.userid.value.length < 4){
		alert("아이디는 4글자 이상이어야 합니다.");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pwd.value == ""){
		alert("비밀번호를 입력하세요.");
		frm.pwd.focus();
		return false;
	}
	if(document.frm.pwd.value != document.frm.pwd_check.value){
		alert("비밀번호가 일치하지 않습니다.")
		frm.pwd.focus();
		return false;
	}
	if(document.frm.reid.value.length == 4){
		alert("아이디 중복체크하세요.");
		return false;
	}
	return true;
}

function check_sel(userid) {
	alert('확인')	
	//location.href=""  여기서 이동
	console.log($(this).find("tr:eq(0)").text());
	return true;
}

$(document).ready(function(){
	  $("#").click(function(){
	    alert("Text: " + $("#test").text());
	  });
	  $("#btn2").click(function(){
	    alert("HTML: " + $("#test").html());
	  });
	});
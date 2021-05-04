/**
 * 
 */

/* 비밀번호, 비밀번호확인 보이기/감추기 */
function showMemberPw() {
	var memberPwShowElement = document.getElementById("memberPwShow");
	var memberPwElement = document.getElementById("memberPw");
	var memberPwConfirmElement = document.getElementById("memberPwConfirm");
	

	if (memberPwShowElement.checked) {
		memberPwElement.type = "text";
		memberPwConfirmElement.type = "text";
	} else {
		memberPwElement.type = "password";
		memberPwConfirmElement.type = "password";
	}
	
}


function checkMemberPw() {
	var memberPwElement = document.getElementById("memberPw");
	var memberPwConfirmElement = document.getElementById("memberPwConfirm");

	var memberPwMessageElement = document.getElementById("memberPwMessage");
	var memberPwConfirmMessageElement = document.getElementById("memberPwConfirmMessage");

	var memberPw = memberPwElement.value;
	var memberPwConfirm = memberPwConfirmElement.value;
	
	if (memberPw == null || memberPw == "" || memberPw.length == 0) {
		memberPwMessageElement.style.color = "red";
		memberPwMessageElement.innerHTML = "비밀번호를 입력하세요.";
		memberPwElement.focus();
		return;
	} else {
		memberPwMessageElement.innerHTML = "";
	}
	
	if (memberPwConfirm == null || memberPwConfirm == "" || memberPwConfirm.length == 0) {
		memberPwConfirmMessageElement.style.color = "red";
		memberPwConfirmMessageElement.innerHTML = "비밀번호확인을 입력하세요.";
		memberPwConfirmElement.focus();
		return;
	} else {
		memberPwConfirmMessageElement.innerHTML = "";
	}
	
	if (memberPw != memberPwConfirm) {
		memberPwConfirmMessageElement.style.color = "red";
		memberPwConfirmMessageElement.innerHTML = "비밀번호와 비밀번호확인이 일치하지 않습니다.";
	} else {
		memberPwConfirmMessageElement.style.color = "green";
		memberPwMessageElement.innerHTML = "";
		memberPwConfirmMessageElement.innerHTML = "비밀번호와 비밀번호확인이 일치합니다.";
	}
}


/* 이메일 도메인 자동 완성 및 직접입력 */

function changeEmailDomain() {
	var email2Element = document.getElementById("email2");
	var emailDomainElement = document.getElementById("emailDomain");
	
	var emailDomain = emailDomainElement.value;
	switch(emailDomain) {
	case "none":
		email2Element.value = "";
		email2Element.readOnly = true;
		break;
	case "input":
		email2Element.value = "@";
		email2Element.readOnly = false;
		email2Element.focus();
		break;
	default:
		email2Element.value = emailDomain;
		break;
	}
}
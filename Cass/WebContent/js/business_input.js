/**
 * 
 */

//비밀번호 보이기/숨기기
function showBusinessPw(){
	var businessPwShowElement = document.getElementById("businessPwShow");
	var businessPwElement = document.getElementById("businessPw");
	var businessPw2Element = document.getElementById("businessPw2");
	
	if(businessPwShowElement.checked) {
		businessPwElement.type = "text";
		businessPw2Element.type = "text";
	} else{
		businessPwElement.type = "password";
		businessPw2Element.type = "password";
	}
}

function bsPwCheck(){
	
	var bsPw1 = document.getElementById("businessPw");
	var bsPw2 = document.getElementById("businessPw2");
	var bsPwMsg = document.getElementById("businessPwMessage");
	var bsPwConMsg = document.getElementById("businessPwConfirmMessage");
	
	var bp1 = bsPw1.value;
	var bp2 = bsPw2.value;
	
	
	if (bp1 == null || bp1 == "" || bp1.length == 0) {
		bsPwMsg.style.color = "red";
		bsPwMsg.innerHTML = "비밀번호를 입력하세요.";
		bsPw1.focus();
		return;
	} else {
		bsPwMsg.innerHTML = "";
	}
	
	if (bp2 == null || bp2 == "" || bp2.length == 0) {
		bsPwConMsg.style.color = "red";
		bsPwConMsg.innerHTML = "비밀번호확인을 입력하세요.";
		bsPw2.focus();
		return;
	} else {
		bsPwConMsg.innerHTML = "";
	}
	
	if (bp1 != bp2) {
		bsPwConMsg.style.color = "red";
		bsPwConMsg.innerHTML = "비밀번호와 비밀번호확인이 일치하지 않습니다.";
	} else {
		bsPwConMsg.style.color = "green";
		bsPwMsg.innerHTML = "";
		bsPwConMsg.innerHTML = "비밀번호와 비밀번호확인이 일치합니다.";
	}
	
	
}
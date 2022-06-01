function val1 () {
    let id = document.getElementById('uid');
    let pwd = document.getElementById('pwd');
    if (id.value === ""){
        alert('아이디를 입력하세요!');
        return false;
    }

    if(pwd.value === "") {
        alert('비밀번호를 입력하세요!');
        return false;
    }
}
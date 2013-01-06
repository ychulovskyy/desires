<style>
#ajaxLogin {
    margin: 15px 0px;
    padding: 0px;
    text-align: center;
    display: none;
    position: absolute;
}

#ajaxLogin .inner {
    width: 260px;
    margin: 0px auto;
    text-align: left;
    padding: 10px;
    border-top: 1px dashed #499ede;
    border-bottom: 1px dashed #499ede;
    background-color: #EEF;
}

#ajaxLogin .inner .fheader {
    padding: 4px;
    margin: 3px 0px 3px 0;
    color: #2e3741;
    font-size: 14px;
    font-weight: bold;
}

#ajaxLogin .inner .cssform p {
    clear: left;
    margin: 0;
    padding: 5px 0 8px 0;
    padding-left: 105px;
    border-top: 1px dashed gray;
    margin-bottom: 10px;
    height: 1%;
}

#ajaxLogin .inner .cssform input[type='text'] {
    width: 120px;
}

#ajaxLogin .inner .cssform label {
    font-weight: bold;
    float: left;
    margin-left: -105px;
    width: 100px;
}

#ajaxLogin .inner .login_message {
    color: red;
}

#ajaxLogin .inner .text_ {
    width: 120px;
}

#ajaxLogin .inner .chk {
    height: 12px;
}

.errorMessage {
    color: red;
}
</style>

<div id='ajaxLogin'>
    <div class='inner'>
        <div class='fheader'>Please Login..</div>

        <form action='${request.contextPath}/j_spring_security_check' method='POST'
              id='ajaxLoginForm' name='ajaxLoginForm' class='cssform'>
            <span>Use admin/admin or user/user</span>
            <p>
                <label for='username'>Login ID</label>
                <input type='text' class='text_' name='j_username' id='username'/>
            </p>

            <p>
                <label for='password'>Password</label>
                <input type='password' class='text_' name='j_password' id='password'/>
            </p>

            <p>
                <label for='remember_me'>Remember me</label>
                <input type='checkbox' class='chk' id='remember_me'
                       name='_spring_security_remember_me'/>
            </p>

            <p>
                <a href='javascript:void(0)' onclick='authAjax();
                return false;'>Login</a>
                <a href='javascript:void(0)' onclick='cancelLogin();
                return false;'>Cancel</a>
            </p>
        </form>

        <div style='display: none; text-align: left;' id='loginMessage'></div>
    </div>
</div>

<script type='text/javascript'>
    var ajaxLogin = document.getElementById('ajaxLogin');
    ajaxLogin.style.left = '100px';
    ajaxLogin.style.top = '100px';

    function showLogin() {
        ajaxLogin.style.display = 'block';
    }

    function cancelLogin() {
        ajaxLogin.style.display = 'none';
    }

    function logoutAjax() {
        $.post('${request.contextPath}/j_spring_security_logout',
                {},
                function (data, status) {
                    setUserName(null);
                }
                );
    }

    function authAjax() {
        $.post('${request.contextPath}/j_spring_security_check',
                {
                    j_username: document.getElementById('username').value,
                    j_password: document.getElementById('password').value
                },
                function (data, status) {
                    if (data.error) {
                        alert(data.error);
                        setUserName(null);
                    } else {
                        setUserName(document.getElementById('username').value);
                        cancelLogin();
                    }
                }
        );
    }

    function setUserName(name) {
        userName = name;
        if (userName) {
            document.getElementById('header').innerHTML = "You are logged in as " + userName + "\<a href='javascript:logoutAjax()'>Logout\</a>";
        } else {
            document.getElementById('header').innerHTML = "You are not logged in. \<a href='javascript:showLogin()'>Login\</a>";
        }
    }
</script>


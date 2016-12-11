/**
 * Created by peiyulin on 2016/12/7.
 */

function onLogin() {



   $('.ui.form')
        .form({
            fields: {
                email: {
                    identifier  : 'email',
                    rules: [
                        {
                            type   : 'empty',
                            prompt : '请输入学号'
                        },
                        {
                            type   : 'length[9]',
                            prompt : '学号为9为数字'
                        }
                    ]
                },
                password: {
                    identifier  : 'password',
                    rules: [
                        {
                            type   : 'empty',
                            prompt : '请输入密码'
                        }
                    ]
                }
            },


            onSuccess: function() {
                document.getElementById("error").style.display="none";
                
                var id=document.getElementsByName("email")[0].value;
                
                setCookie("stuid",id);

                
                document.getElementById("form").submit();


                return true; // false is required if you do don't want to let it submit

            },
            onFailure: function() {
                document.getElementById("error").style.display="block";
                return false; // false is required if you do don't want to let it submit
            }

        });





}

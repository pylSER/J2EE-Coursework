
function showModal(){
	$('#modal')
	  .modal('show')
	;
	
	
	$.ajax({ url: "http://localhost:8080/J2EE2/CountGetter", context: document.body, success: function(data){
		document.getElementById("total").innerText=data.total;
		document.getElementById("user").innerText=data.user;
		document.getElementById("visitor").innerText=data.visitor;
      }});
	
	
	
}




function onLogin() {



   $('.ui.form')
        .form({
            fields: {
                email: {
                    identifier  : 'email',
                    rules: [
                        {
                            type   : 'empty',
                            prompt : '请输入学号/姓名'
                        },
                        
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

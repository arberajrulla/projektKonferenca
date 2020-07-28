//document.write("JavaScript is a simple language for javatpoint learners");  
//var parg = document.createElement('div');
//parg.innerText = "ooooooooooooooooooooo";
//document.getElementById('pgg2').appendChild(parg);


//function eshteBosh() {
//      if (document.getElementById("fushaid") == null) {       
//        //document.getElementById("pgg").innerHTML = "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh";
//      } else { //#{autentikim.hyr()}
//    	  }}

//<h:outputScript library = "js" name="script.js"></h:outputScript>




window.onload = function() {
	
    load();

    function load() {
    	
    	const id = document.querySelector("#form.gridFushat.fushaId");
    	const pass = document.querySelector("#form.fushaPassword");
    	const hyrBtn = document.querySelector("#form.gridFushat.hyr");
    	
    	if(hyrBtn){
    		
        	hyrBtn.addEventListener("click", function(){
        		
        		document.getElementById("form.gridFushat.errorId").style.color = "red";
        		document.getElementById("form.gridFushat.errorId").innerHTML = "";
        		document.getElementById("form.gridFushat.errorPass").style.color = "red";
        		document.getElementById("form.gridFushat.errorPass").innerHTML = "";
        		

        		if(document.getElementById("form.gridFushat.fushaId").value == ""){
        			document.getElementById("form.gridFushat.errorId").innerHTML = "ID nuk mund te jete bosh!";
        		}
        		

        		
        		if(document.getElementById("form.fushaPassword").value == ""){
        			document.getElementById("form.errorPass").innerHTML = "Fjalekalimi nuk mund te jete bosh!";
        		} else if(document.getElementById("form.fushaPassword").value.length < 6){
        			document.getElementById("form.errorPass").innerHTML = "Fjalekalimi duhet te kete te pakten 6 karaktere";
        		}
        	});

    	}
    }}

function validate()  
{  
 var letterNumber = /^[a-zA-Z]+$/;  
 var name = document.getElementById("cardholdername").value;
 var ccv = document.getElementById("ccv").value;
 var creditcardno = document.getElementById("cardnumber").value;
 
 if((name.value.match(letterNumber))||(name.match(/^[\s]/))||(ccv.match(/^[\s]/))||(creditcardno.match(/^[\s]/)))   
  {  
   return true;  
  } 
else  
  {   
   alert("Error in input field");   
   return false;   
  } 
 var
  }  
if(!jQuery)
{ throw new Error("Validation requires jQuery") }
$(function($) {
    $( "#address" ).on('input', function(){
    	var input = $(this).val();
    	if(!input.match("^[a-zA-Z]")){
    		$('#errorMessage').show();
    		$('#errorMessage').text(
    				'Error: Enter valid city');
    		$('#address').val("");
    	}
    });
    
  });


$(function($) {
    $( "#checkindate" ).on('input', function(){
    	var input = $(this).val();
    	if(!input.match("^[0-9]-")){
    		$('#errorMessage').show();
    		$('#errorMessage').text(
    				'Error: Enter valid check in date');
    		$('#checkindate').val("");
    	}
    });
    
  });
$(function($) {
    $( "#checkoutdate" ).on('input', function(){
    	var input = $(this).val();
    	if(!input.match("^[0-9-]")){
    		$('#errorMessage').show();
    		$('#errorMessage').text(
    				'Error: Enter valid check out date');
    		$('#checkoutdate').val("");
    	}
    });
    
  });
$(function($) {
    $( "#firstName" ).on('input', function(){
    	var input = $(this).val();
    	if(!input.match("[a-zA-Z]")){
    		$('#error').show();
    		$('#error').text(
    				'Error: Enter valid first name');
    		$('#firstName').val("");
    	}
    });
    
  });
$(function($) {
    $( "#lastName" ).on('input', function(){
    	var input = $(this).val();
    	if(!input.match("[a-zA-Z]")){
    		$('#error').show();
    		$('#error').text(
    				'Error: Enter valid last name');
    		$('#lastName').val("");
    	}
    });
    
  });

$("#address").keyup(function() {
    var $th = $(this);
    $th.val( $th.val().replace(/[^a-zA-Z0-9]/g, function(str) { alert('You typed " ' + str + ' ".\n\nPlease use only letters and numbers.'); return ''; } ) );
});

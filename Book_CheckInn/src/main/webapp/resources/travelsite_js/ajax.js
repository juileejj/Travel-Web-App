
	  function GetXmlHttpObject()
      {
          var xmlHttp = null;
          try
          {
              // Firefox, Opera 8.0+, Safari
              xmlHttp = new XMLHttpRequest();
          } catch (e)
          {
              // Internet Explorer
              try
              {
                  xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
              } catch (e)
              {
                  xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
              }
          }
          return xmlHttp;
      }
	
	  var xmlHttp;
      xmlHttp = GetXmlHttpObject();
      function getResults() {
    	  var latlongval;
          if (xmlHttp == null)
          {
              alert("Your browser does not support AJAX!");
              return;
          }
          var latlongval=document.getElementById("latlong").value;
          var e = document.getElementById("adults");
          var adults = e.options[e.selectedIndex].value;
          var checkindate= document.getElementById("checkindate").value;
          var checkoutdate=document.getElementById("checkoutdate").value;
          var query = "?action=gethotels&address=" + latlongval + "&checkindate=" + checkindate
          + "&checkoutdate=" + checkoutdate+ "&adults=" + adults;
          console.log(query);
          xmlHttp.onreadystatechange = function stateChanged()
          {
              if (xmlHttp.readyState == 4)
              {
                  var json = JSON.parse(xmlHttp.responseText);
                  displayHotels(json);
                  
              }
          };
          xmlHttp.open("POST", "gethotels.htm", true);
          xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
          xmlHttp.send(query);
          return false;
      }
      
      function displayHotels(json)
      {
    	  document.getElementById("hoteldiv").style.visibility="visible";
    	  document.getElementById("hoteldiv").innerHtml = "";
    	  if(json.hotels.length>0)
    		  {
    		  var ul = document.createElement("ul");
    		  ul.setAttribute("class", "list-group");
              
              
              for (var count = 0; count < json.hotels.length; count++)
            	  {
                  var li = document.createElement("li");
                  li.setAttribute("class", "list-group-item");
                  var divrow = document.createElement("div");
                  divrow.setAttribute("class", "row");
                  var divcolsm2=document.createElement("div");
                  divcolsm2.setAttribute("class", "col-sm-2");
                  var thumbnail= document.createElement("img");
                  thumbnail.setAttribute("src",json.hotels[count].thumbnailUrl);
                  divcolsm2.appendChild(thumbnail);
                  divrow.appendChild(divcolsm2);
                  
                  var divcolsm8=document.createElement("div");
                  divcolsm8.setAttribute("class", "col-sm-8");
                  var hotelname = document.createElement("a");
                  hotelname.setAttribute("href",json.hotels[count].detailsurl);
                  var name = document.createTextNode(json.hotels[count].hotelName);
                  hotelname.appendChild(name);
                  divcolsm8.appendChild(hotelname);
                  var desc = document.createElement("p");
                  var details = document.createTextNode(json.hotels[count].description);
                  desc.appendChild(details);
                  divcolsm8.appendChild(desc);
                  divrow.appendChild(divcolsm8);
                  
                  var divcolsm2button=document.createElement("div");
                  divcolsm2button.setAttribute("class","col-sm-2");
                  var button = document.createElement("a");
                  button.setAttribute("href","bookhotel.htm?hotelID="+json.hotels[count].hotelID);
                  button.setAttribute("class","btn btn-success");
                  button.setAttribute("role","button");
                  var textbtn = document.createTextNode("Book Now");
                  button.appendChild(textbtn);
                  divcolsm2button.appendChild(button);
                  divrow.appendChild(divcolsm2button);
                  li.appendChild(divrow);
                  ul.appendChild(li);
            	  }
              document.getElementById("hoteldiv").appendChild(ul);
    		  }
      }
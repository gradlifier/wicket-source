
// Saves options to localStorage.
function save_options() {
  localStorage["server"] = document.getElementById("server").value;
  localStorage["port"] = document.getElementById("port").value;
  localStorage["password"] = document.getElementById("password").value;
  
  // Update status to let user know options were saved.
  var status = document.getElementById("status");
  status.innerHTML = "&nbsp;Saved.&nbsp;";
  setTimeout(function() {
    status.innerHTML = "";
  }, 750);
}

function attach_save_listener() {
  saveButton = document.getElementsByTagName("button")[0];
  saveButton.addEventListener('click', save_options);
  //alert("Button " + saveButton);
}

// Restores select box state to saved value from localStorage.
function restore_options() {
  if (! localStorage["server"]) {
	  document.getElementById("server").value = "localhost";
	  document.getElementById("port").value = "9123";
	  document.getElementById("password").value = "";
	  return;
  }
  
  document.getElementById("server").value = localStorage["server"];
  document.getElementById("port").value = localStorage["port"];
  document.getElementById("password").value = localStorage["password"];
  
}

function onLoadCompleted() {
  attach_save_listener();
  restore_options();
}

window.addEventListener('load', onLoadCompleted);

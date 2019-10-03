function validate() {
    if (document.f.email.value == "" && document.f.password.value == "") {
        alert("Email and password are required");
        document.f.email.focus();
        return false;
    }
    if (document.f.email.value == "") {
        alert("Email is required");
        document.f.eamil.focus();
        return false;
    }
    if (document.f.password.value == "") {
	    alert("Password is required");
	    document.f.password.focus();
        return false;
    }
} 
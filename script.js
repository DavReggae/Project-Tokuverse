window.addEventListener("scroll", function() {
    var signupLoginNav = document.getElementById("signup-login");
    if (window.pageYOffset > 0) {
      signupLoginNav.classList.add("hidden");
    } else {
      signupLoginNav.classList.remove("hidden");
    }
  });
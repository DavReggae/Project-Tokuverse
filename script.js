window.addEventListener("scroll", function() {
    var signupLoginNav = document.getElementById("signup-login");
    if (window.pageYOffset > 0) {
      signupLoginNav.classList.add("hidden");
    } else {
      signupLoginNav.classList.remove("hidden");
    }
  });
  function setFormMessage(formElement, type, message){
    const messageElement = formElement.querySelector(".form__message");

    messageElement.textContent = message;
    messageElement.classList.remove("form__message--success", "form__message--error");
    messageElement.classList.add('form__message--${type}');
}

//setFormMessage(loginForm, "success", "You're logged in!");

function setInputError(inputElement, message){
    inputElement.classList.add("form__input--error");
    inputElement.parentElement.querySelector(".form__input-error-message").textContent = message;
}

function clearInputError(inputElement){
    inputElement.classList.remove("form__input--error");
    inputElement.parentElement.querySelector(".form__input-error-message").textContent = "";
}

document.addEventListener("DOMContentLoaded", () =>{
    const loginForm = document.querySelector("#login");
    const createAccountForm = document.querySelector("#createAccount");

    document.querySelector("#linkCreateAccount").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.add("form--hidden");
        createAccountForm.classList.remove("form--hidden");
    });

    document.querySelector("#linkLogin").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.remove("form--hidden");
        createAccountForm.classList.add("form--hidden");
    });

    loginForm.addEventListener("submit", e => {
        e.preventDefault();

        //Perform your AJAX/Fetch Login

        setFormMessage(loginForm, "error", "Invalid Username/Password");
    });

    document.querySelector(".form__input").forEach(inputElement => {
        inputElement.addEventLstener(" blur", e => {
            if(e.target.id === "signupUsername" && e.target.value.length > 0 && e.target.value.length < 10) {
               setInputError(inputElement, "Username must be at least 10 characters in length");
            }
        });
  
        //loginForm.addEvent
        inputElement.addEventListener("input", e => {
            clearInputError(inputElement);
        });
    });
});
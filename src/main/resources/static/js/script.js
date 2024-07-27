
let currentTheme = getTheme();

// initial changes is called
document.addEventListener('DOMContentLoaded', ()=>{
    changeTheme(currentTheme);
});

//TODO
function changeTheme(){
    // set the theme in web page
    document.querySelector('html').classList.add(currentTheme);

    // set the listener to change theme button
    let changeThemebutton = document.querySelector('#theme_change_button');
      
    changeThemebutton.addEventListener("click",(event) =>{
        let oldtheme = currentTheme;
        // console.log("change theme button clicked");
        if(currentTheme === "dark"){
            // theme ko light
            currentTheme ="light"
        }
        else{
            currentTheme="dark";
        }
        changePageTheme(currentTheme,oldtheme);
    });
}
// set theme to localstorage
function setTheme(theme){
    localStorage.setItem('theme',theme);
}
// get theme from localstorage
function getTheme(){
    let theme = localStorage.getItem('theme');
    return theme ? theme : "light";
}
// To change the theme of the page
function changePageTheme(theme,oldtheme){
     // localstorage mein update karenge
     setTheme(theme);
     // remove the current theme
     if(oldtheme)
     document.querySelector('html').classList.remove(oldtheme);
     // set the current theme
     document.querySelector('html').classList.add(theme);
     // change the text of button
     document.querySelector("#theme_change_button").querySelector("span").textContent = currentTheme == "light" ? "Dark":"Light" ;
}
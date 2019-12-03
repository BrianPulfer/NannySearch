//import 'bootstrap';

SEARCH_BAR_MARGIN = "200px"

let search_bar = document.getElementById('SearchBar');
let searchButton = document.getElementById("SearchButton");

if(document.getElementById("ResultsDiv") === null){
    search_bar.parentElement.style.marginTop = SEARCH_BAR_MARGIN;
}

search_bar.addEventListener('keyup',function(e){
    if(search_bar.value == ""){
        // Set the animation when search finishes (slide down)
        search_bar.parentElement.style.animation = "0.5s ease 0s 1 normal forwards running searchEnd";

        setTimeout(function(){
            search_bar.parentElement.style.marginTop = SEARCH_BAR_MARGIN;
        }, 500);
    } else{
        // Set the animation when search begins (slide up)
        search_bar.parentElement.style.animation = "0.5s ease 0s 1 normal forwards running searchStart";

        setTimeout(function(){
            search_bar.parentElement.style.marginTop = "0px";
        }, 500);
    }

    if(e.keyCode ===13){
        search();
    }
});

searchButton.addEventListener('click', function(e){
    search();
})


function search(){
    let query = search_bar.value
    console.log("Research started: "+query)
}

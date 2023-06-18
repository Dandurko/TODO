let dateElems=document.getElementsByClassName('date');
for(const element of dateElems){
    let dateElem=element;
    let seconds=dateElem.getAttribute('value');
    dateElem.innerHTML=seconds;
    getDate(seconds,dateElem);
}

function getDate(seconds,dateElem){
    let duration = moment.duration(seconds, 'seconds');
    let days = duration.days();
    let hours = duration.hours();
    let minutes = duration.minutes();
    let sec=duration.seconds();

    dateElem.innerHTML='Days: '+days+' Hours: '+hours+' Minutes: '+minutes+' Seconds: '+sec;
}

document.querySelectorAll(".nav-link").forEach((link) => {
    if (link.href === window.location.href) {
        link.classList.add("active");
        link.setAttribute("aria-current", "page");
    }
});
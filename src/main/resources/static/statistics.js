
function getDate(seconds,dateElem){
    let duration = moment.duration(seconds, 'seconds');
    let days = duration.days();
    let hours = duration.hours();
    let minutes = duration.minutes();
    let sec=duration.seconds();

    dateElem.innerHTML='Days: '+days+' Hours: '+hours+' Minutes: '+minutes+' Seconds: '+sec;
}

let timeInSecondsElement=document.getElementById('timeInSeconds')
let seconds=timeInSecondsElement.getAttribute('value');

getDate(seconds,timeInSecondsElement);




let elMatches = document.querySelector("#matches");
let allMatches = [];
var d = new Date();
let currentDay = d.getDate();

function renderMatches() {

             for (let i = 0; i < allMatches.matches.length; i++) {
                console.log(allMatches);
                  
                elMatches.innerHTML += `
            <tr>
            <td><img src="${allMatches.matches[i].competition.area.ensignUrl}" width="40" height="40"/></td>   
            <td>${allMatches.matches[i].competition.name}</td>   
            <td>${allMatches.matches[i].homeTeam.name}</td>
            <td>${allMatches.matches[i].awayTeam.name}</td>
            <td>${allMatches.matches[i].matchday}/`+ (d.getMonth()+1) +`/2020</td>
            </tr>
             `;
            
        }
        
 }



function getMatches() {
            $.ajax({
                headers: { 'X-Auth-Token': '3c68e29752c549acbc9a7b285932fb1c' },
                url: 'http://api.football-data.org/v2/matches?status=FINISHED&dateFrom=2020-05-' + (currentDay - 7) + '&dateTo=2020-05-' + currentDay + '',
                dataType: 'json',
                type: 'GET',
            }).done(function (response) {
                allMatches = response;
                renderMatches();
                
            });
        }
        
        
        
getMatches();        
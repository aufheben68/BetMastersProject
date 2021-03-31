        let elPdStandings = document.querySelector("#pdStandings")
        let elPlStandings = document.querySelector("#plStandings")
        let elSaStandings = document.querySelector("#saStandings");
        let elFlStandings = document.querySelector("#flStandings");
        let elBlStandings = document.querySelector("#blStandings");
        let pdStandings = [];
        let plStandings = [];
        let saStandings = [];
        let blStandings = [];
        let flStandings = [];
        

        function renderBlStandings() {
        
            for (let i = 0; i < blStandings.standings[0].table.length; i++) {

            elBlStandings.innerHTML += `
                
             <tr>
                <td>${blStandings.standings[0].table[i].position}</td>
                <td>${blStandings.standings[0].table[i].team.name}</td>
                <td>${blStandings.standings[0].table[i].playedGames}</td>
                <td>${blStandings.standings[0].table[i].goalsFor}</td>
                <td>${blStandings.standings[0].table[i].goalsAgainst}</td>
                <td>${blStandings.standings[0].table[i].points}</td>
            </tr>
            `;
        }
        }

        function renderFlStandings() {
           
            for (let i = 0; i < flStandings.standings[0].table.length; i++) {

            elFlStandings.innerHTML += `
                
             <tr>
                <td>${flStandings.standings[0].table[i].position}</td>
                <td>${flStandings.standings[0].table[i].team.name}</td>
                <td>${flStandings.standings[0].table[i].playedGames}</td>
                <td>${flStandings.standings[0].table[i].goalsFor}</td>
                <td>${flStandings.standings[0].table[i].goalsAgainst}</td>
                <td>${flStandings.standings[0].table[i].points}</td>
            </tr>
            `;
        }
        }

        function renderSaStandings() {
           
           for (let i = 0; i < saStandings.standings[0].table.length; i++) {
             
           elSaStandings.innerHTML += `
               
            <tr>
               <td>${saStandings.standings[0].table[i].position}</td>
               <td>${saStandings.standings[0].table[i].team.name}</td>
               <td>${saStandings.standings[0].table[i].playedGames}</td>
               <td>${saStandings.standings[0].table[i].goalsFor}</td>
               <td>${saStandings.standings[0].table[i].goalsAgainst}</td>
               <td>${saStandings.standings[0].table[i].points}</td>
           </tr>
           `;
       }
       }

       function renderPlStandings() {
           
           for (let i = 0; i < plStandings.standings[0].table.length; i++) {
             
           elPlStandings.innerHTML += `
               
            <tr>
               <td>${plStandings.standings[0].table[i].position}</td>
               <td>${plStandings.standings[0].table[i].team.name}</td>
               <td>${plStandings.standings[0].table[i].playedGames}</td>
               <td>${plStandings.standings[0].table[i].goalsFor}</td>
               <td>${plStandings.standings[0].table[i].goalsAgainst}</td>
               <td>${plStandings.standings[0].table[i].points}</td>
           </tr>
           `;
       }
       }

       function renderPdStandings() {
           
           for (let i = 0; i < pdStandings.standings[0].table.length; i++) {
             
           elPdStandings.innerHTML += `
               
            <tr>
               <td>${pdStandings.standings[0].table[i].position}</td>
               <td>${pdStandings.standings[0].table[i].team.name}</td>
               <td>${pdStandings.standings[0].table[i].playedGames}</td>
               <td>${pdStandings.standings[0].table[i].goalsFor}</td>
               <td>${pdStandings.standings[0].table[i].goalsAgainst}</td>
               <td>${pdStandings.standings[0].table[i].points}</td>
           </tr>
           `;
       }
       }

        

        function getFlStandings() {
            $.ajax({
                headers: { 'X-Auth-Token': '3c68e29752c549acbc9a7b285932fb1c' },
                url: 'http://api.football-data.org/v2/competitions/FL1/standings',
                dataType: 'json',
                type: 'GET',
            }).done(function (response) {
                flStandings = response;
                
                
                renderFlStandings();
                

            });
        }

        function getPlStandings() {
            $.ajax({
                headers: { 'X-Auth-Token': '3c68e29752c549acbc9a7b285932fb1c' },
                url: 'http://api.football-data.org/v2/competitions/PL/standings',
                dataType: 'json',
                type: 'GET',
            }).done(function (response) {
                plStandings = response;
                
                
                renderPlStandings();
                

            });
        }

        function getBlStandings() {
            $.ajax({
                headers: { 'X-Auth-Token': '3c68e29752c549acbc9a7b285932fb1c' },
                url: 'http://api.football-data.org/v2/competitions/BL1/standings',
                dataType: 'json',
                type: 'GET',
            }).done(function (response) {
                blStandings = response;
                
                renderBlStandings();
               
            });
        }

        function getSaStandings() {
            $.ajax({
                headers: { 'X-Auth-Token': '3c68e29752c549acbc9a7b285932fb1c' },
                url: 'http://api.football-data.org/v2/competitions/SA/standings',
                dataType: 'json',
                type: 'GET',
            }).done(function (response) {
                saStandings = response;
                
                renderSaStandings();
               
            });
        }

        function getPdStandings() {
            $.ajax({
                headers: { 'X-Auth-Token': '3c68e29752c549acbc9a7b285932fb1c' },
                url: 'http://api.football-data.org/v2/competitions/PD/standings',
                dataType: 'json',
                type: 'GET',
            }).done(function (response) {
                pdStandings = response;
                
                renderPdStandings();
               
            });
        }

        
        getBlStandings();
        getFlStandings();
        getSaStandings();
        getPlStandings();
        getPdStandings();
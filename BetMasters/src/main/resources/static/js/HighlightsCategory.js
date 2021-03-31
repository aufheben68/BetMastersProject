"use script";

fetch('https://www.scorebat.com/video-api/v1/')
.then(response => response.json())
.then(data => highlightsApp(data));


function highlightsApp(videos){

    const videosEl=document.querySelector("#video");
    const paginationEl=document.querySelector("#pagination");
    let current_page=1;
    let rows=5;

    function DisplayVideos(videos, videosEl, videos_per_page, page){

        videosEl.innerHTML="";
        page--;

        let start=videos_per_page*page;
        let end= start+videos_per_page;
        let paginatedVideos=videos.slice(start, end);

        for(let i=0; i<paginatedVideos.length; i++){

            let video=paginatedVideos[i];

            videosEl.innerHTML+=
                `<div>
                    <div class="row" id="nameStyles" >
                        ${video.side1.name}   vs   ${video.side2.name}
                    </div>
                    <div class="row" style=" font-family:'Roboto Condensed', sans-serif; font-size:13px;">
                        in ${video.competition.name}
                    </div>
                    <div class="row" id="videoStyles">
                        <iframe src="${$('iframe', video.embed).attr('src')}" style= "width:430px; height:320px; " scrolling="no" ></iframe>
                    </div>
                    <br>
                </div>
                <br><br>
                `;
        }
    }

    function SetupPagination(videos, videosEl, videos_per_page){
        videos.innerHTML="";

        let page_count=Math.ceil(videos.length /videos_per_page);
        for(let i=1; i<page_count+1; i++){
            let btn=PaginationButton(i,videos);
            videosEl.appendChild(btn);
        }
    }

    function PaginationButton(page,videos){
        let button=document.createElement('button');
        button.innerText=page;

        if(current_page==page)button.classList.add('active');

        button.addEventListener('click',function(){
            current_page=page;
            DisplayVideos(videos,videosEl, rows, current_page);
            })
        return button;
    }


DisplayVideos(videos, videosEl, rows, current_page);
SetupPagination(videos, paginationEl, rows);



}
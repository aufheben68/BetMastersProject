"use script";

fetch("articles.json")
.then(function(resp){
    return resp.json();
})
.then(articlesApp)

function articlesApp(articles){

    const articlesEl=document.querySelector("#article");
    const paginationEl=document.querySelector("#pagination");
    let current_page=1;
    let rows=6;

    function DisplayArticle(articleID,articlesEl){
        
        const articl = articles.map(article=> article).filter(article=>article.id==articleID)
        console.log(articl[0].title);

        articlesEl.innerHTML=`
                       <div style="font-size: 22px; font-weight: 700;">${articl[0].title}</div> <br>
                       <img src="${articl[0].image}" class="img-rounded img-responsive center" style="width:340px; height:250px;"><br>
                       <div>${articl[0].prologue}</div>
                       <div>${articl[0].text}</div>
                       `;

        $("#pagination").hide();
       
     }


    function DisplayArticles(articles, articlesEl, articles_per_page, page){

        articlesEl.innerHTML="";
        page--;

        let start=articles_per_page*page;
        let end= start+articles_per_page;
        let paginatedArticles=articles.slice(start, end);

        for(let i=0; i<paginatedArticles.length; i++){

            let article=paginatedArticles[i];
            
            articlesEl.innerHTML+=
                `
                <div>
                    <div class="row"">
                        <div class="col-7">
                            <div class="col-*" id="clickable" style="font-size: 16px;">${article.title}
                            </div><br>
                            <div class="col-*" style="font-size: 12px;">${article.prologue}</div>
                            <button class="col-*" type="${article.id}" id="MyButton" class="button-4" style="height:5px; margin-top:0px; margin-left:90px; background-color:rgb(196,18,78); color:white;" >Read more</button>
                        </div>
                        <div class="col-5">
                            <img src="${article.image}" class="img-rounded img-responsive" style="width:185px; height:165px;">
                            <div class="col-*" style=" font-size: 10px; margin-top:8px;">Επιμέλεια: ${article.writter}</div>
                            <div class="col-*" style="font-size: 10px; ">${article.published}</div>
                        </div>
                    </div>
                </div>
                <hr>
                `;
        }
    }
    

    function SetupPagination(articles, articlesEl, articles_per_page){
        articles.innerHTML="";

        let page_count=Math.ceil(articles.length /articles_per_page);
        for(let i=1; i<page_count+1; i++){
            let btn=PaginationButton(i,articles);
            articlesEl.appendChild(btn);
        }
    }

    function PaginationButton(page,articles){
        let button=document.createElement('button');
        button.innerText=page;

        if(current_page==page)button.classList.add('active');

        button.addEventListener('click',function(){
            current_page=page;
            DisplayArticles(articles,articlesEl, rows, current_page);
        })
        return button;
    }

    DisplayArticles(articles, articlesEl, rows, current_page);
    SetupPagination(articles, paginationEl, rows);

    $(document).on('click','#MyButton',function(event){
        var articleID=$(this).attr('type');
        console.log(articleID);
        DisplayArticle(articleID,articlesEl);
    })

}




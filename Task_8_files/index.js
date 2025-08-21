async function loadtasks(){
    fetch("http://localhost:9090/api/taskmanger/all")
    .then((res)=>{
        if (res.ok){
            return res.json();
        }
    })
    .then((data)=> {
        const tasklist = document.getElementById("task-list");
        tasklist.innerHTML="";
        data.forEach(task => {
            const event = document.createElement("li");
            event.innerHTML = `${task.title}: ${task.description} 
                <button onclick="deleteTask(${task.id},this)">delete</button>`;
            event.setAttribute("onclick",`getbyTaskid(${task.id})`);
            tasklist.appendChild(event);
        });
    })
    .catch(err => console.log(err));

}

async function addTask(id) {
    const title = document.getElementById("task-input").value;
    const description = document.getElementById("task-desc").value;
    if(typeof id === "undefined"){
         taskdata = {
        "title" : title,
        "description" : description
        }
    }else{
         taskdata = {
        "id" : id,
        "title" : title,
        "description" : description
        }
    }
   
    fetch("http://localhost:9090/api/taskmanger/create",{
        method : "POST",
        headers : {
            "Content-Type" : "application/json"
        },
        body : JSON.stringify(taskdata)
    })
    .then(res => {
        if(res.ok){
            loadtasks();
            console.log("done inserting new task");
        }

    })
    .catch(error => console.log(error));
}

async function deleteTask (id , task) {
    const currenTask = task.parentElement;
    fetch(`http://localhost:9090/api/taskmanger/delete?taskId=${id}`,{
        method : "DELETE"
    })
    .then(res=> {
        if(res.ok){
            currenTask.remove();
            alert("deleted");
        }
    })
}
async function getbyTaskid(id){
    fetch(`http://localhost:9090/api/taskmanger/by?TaskId=${id}`)
    .then(res=> {
        if(res.ok){
            return res.json();
        }
    })
    .then(data => {
            document.getElementById("task-input").value = data.title;
            document.getElementById("task-desc").value = data.description;
            let subbutton = document.getElementById("sub-button");
            subbutton.setAttribute("onclick",`update()`);
    })
    .catch(error => console.log(error));
}
window.onload = loadtasks;
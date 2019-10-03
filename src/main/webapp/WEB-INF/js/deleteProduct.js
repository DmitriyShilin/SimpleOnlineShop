let del = document.getElementsByClassName("delProduct");
let confirmIt = function(event) {
	if(!confirm("Are you sure?")) event.preventDefault();
};
for(let i = 0; i < del.length; i++){
	del[i].addEventListener("click", confirmIt, false);
}

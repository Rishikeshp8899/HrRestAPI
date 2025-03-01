let remove=document.querySelector(".remove")
let accept=document.querySelector(".accept")

let add=document.querySelector(".sucessfullyadd")
let notadd=document.querySelector(".sucessfullyremove")
console.log(remove)
console.log(accept)
console.log(add)
console.log(notadd)
if(remove!=null)
{
	alert("Failed to reject please try again ")
}
else if(accept!=null)
{
	alert("Failed to accept the request please try again")
}
else if(add!=null)
{
	alert("Request Aceept Sucessfully Please Fullfllied the request");
}
else if(notadd!=null)
{
	alert("Request rejected sucessfully")
}

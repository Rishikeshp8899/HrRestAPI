let notadded=document.querySelector(".failedtosend")
let added=document.querySelector(".sendedtohr")
let reuirementfull=document.querySelector(".reuirementfull")
console.log(notadded)
console.log(added)

if(notadded!=null)
{
	alert("failed to send the request to hr")
}
else if(added!=null)
{
	alert("request send to hr sucessfully ")
}
else if(reuirementfull!=null)
{
	alert("requirment Fulled")
}


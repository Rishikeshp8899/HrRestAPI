let datenotvalid=document.querySelector(".datenotvalid")
let failedtopost=document.querySelector(".failedtopost")
let jobposted=document.querySelector(".jobposted")
let assigned=document.querySelector(".assigned")

if(datenotvalid!=null)
{
	alert("invalid date....!!! please select date greater than today")
}
else if(failedtopost!=null)
{
	alert("falied to post the job request try again...!!!")
}
else if(jobposted!=null)
{
	alert("job posted Sucessfully")
}
else if(assigned!=null)
{
	alert("interviewer assigned Sucessfully")
}
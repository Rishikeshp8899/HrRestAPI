let failedtosend=document.querySelector(".failedtosend")
let failedtoupdate=document.querySelector(".failedtoupdate")
let offerlettersend=document.querySelector(".offerlettersend")
let requiredMentisFull=document.querySelector(".requiredMentisFull")
if(failedtosend!=null)
{
	alert("failed to send the offer letter try again")
}
else if(failedtoupdate!=null)
{
	alert("failed to update the status the of candidate please contact to admin")
}
else if(offerlettersend)
{
	alert("offer letter send Sucessfully ")
}
else if(requiredMentisFull!=null)
{
	alert("Required Ment is full You cannot added new candidate Delete it..!!!")
}
let caraosal=document.querySelector(".carosal")
let data=["carosal1.jpg","caraosal2.jpg","carosal3.jfif"]
let index=0;
let service_container=document.querySelector(".srvice_container")
    setInterval(() => 
    {
        index++
        if(index>data.length-1)
        {
            index=0
        }

        if(index<0)
        {
            index=data.length-1
        } 
        caraosal.src=data[index]
    }, 1500);

    let servicedata=
        [
            {src:"UPI-NEW-ICON.webp",heading:"UPI Payment Platform",desc:"UPI real-time payment eco system is a global success story that is growing leaps & bounds and processing billions of transactions monthly. Mindgate’s state of art UPI payments suite enables banks & FIs to on-board and process UPI payments."},
            {src:"NETC-NEW-ICON.webp",heading:"NETC Issuer Platform",desc:"National Toll collection platform is today connected to 800 plus tolls plazas and processing millions of transactions. Mindgate Issuer platform is an end to end issuer platform to issue RFID tags, recharge and manage the payments life cycle."},
            {src:"Bill-Presentment-_-Payments.webp",heading:"BBPS Processing Platform",desc:"A Comprehensive, Integrated and interoperable bank-centric platform offering bill payment services to customers online or through a network of agents, allowing multiple payment modes and instant confirmation."},
            {src:"MMP-NEW-ICON.webp",heading:"Merchant Management platform",desc:"Comprehensive merchant management platform built on a robust technology platform enabling the acquiring institution to manage the entire spectrum of merchant lifecycle management."},
            {src:"RECON-NEW-ICON.webp",heading:"Reconciliation & Settlement as a service",desc:"Mindgate helps financial institutions around the world in facilitating comprehensive reconciliation of digital payment transactions, enabling flexible and robust settlement and end-to-end dispute management and chargeback processing."},
            {src:"COMPLOANCE-NEW-ICON.webp",heading:"Compliance Management",desc:"A Compliance Management platform to help comply with the latest compliance requirements."}

        ]
    function service()
    {
        servicedata.map((v)=>
        {
            console.log(v);
            service_container.innerHTML+=
            `
            <div class="Carrer_card">
                <img src=${v.src} alt="">
                <h1>${v.heading}</h1>
                <p>${v.desc}</p>
                <a href="#" class="learn">LEARN MORE</a>
            </div>

            `
        })

    }

    function mains()
    {
        service()
    }
    mains()
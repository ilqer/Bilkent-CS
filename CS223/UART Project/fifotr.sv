`timescale 1ns / 1ps




module fifotr
	(
	   input clk_100MHz,                              // FPGA clock   
	   input TXBUF_write,                    // signal start writing to FIFO
	   input RXBUF_write,                   // signal start reading from FIFO
	   input [7:0] TXBUF,                  // data word into FIFO
	   input [7:0] RXBUF,                  // data word out of FIFO
	   //output empty,                           // FIFO is empty (no read)
	   //output full,	                           // FIFO is full (no write)
	   output [7:0] TXoldest,
	   output [7:0] RXdisplay,
	   output reg [7:0] hxxt[4],
       output reg [7:0] hxxr[4]
);



        //reg [7:0] hxxt[4];
        //reg [7:0] hxxr[4];
        reg  lastr = 0;
        reg lastt = 0;
        
        initial begin 
            for(int i = 0;i<$size(hxxt);i++)begin
                hxxt[i] = 0;
                hxxr[i] = 0;
            end
        end
        
            always@(posedge clk_100MHz)
        if(RXBUF_write)begin
            for(int i = 2;i>=0;i--)begin
            hxxt[i+1] = hxxt[i];
            end
            hxxt[0] = TXBUF;
//            if(lastt<3)
//            lastt++;
        end
        
        
        
    always@(posedge clk_100MHz)
        if(TXBUF_write)begin
            for(int i = 2;i>=0;i--)begin
            hxxr[i+1] = hxxr[i];
            end
            hxxr[0] = RXBUF;
//            if(lastr<3)
//            lastr++;
        end
        
        
     assign RXdisplay = hxxr[3];
     assign TXoldest = hxxt[3];
        
endmodule
        
        
        
        
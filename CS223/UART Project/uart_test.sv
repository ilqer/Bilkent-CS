`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 06.05.2024 07:00:19
// Design Name: 
// Module Name: uart_test
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module uart_test(
    input clk_100MHz,       // basys 3 FPGA clock signal
    input btnR,            // btnR    
    input rx,               // USB-RS232 Rx
    input btn,              // btnC (read and write FIFO operation)
    input btnD,
    input btnU,
    input btnL,
    input [7:0] TXBUF,
    output tx,              // USB-RS232 Tx
    output [3:0] an,        // 7 segment display digits
    output [0:6] seg,       // 7 segment display segments
    output [7:0] LED,       // data byte display
    output [7:0] fifotxout
    //output tx_done,   //delete after
    //output tx_fifo_not,    //delete after
    //output tx_empt, //delete after
    //output in,
    //output in0,
    //output in2
    );
    
    reg [7:0] RXBUF;
    reg rx_done_tick;
    reg tx_done_tick;
    reg [7:0] TXoldest;
    reg [7:0] RXdisplay;
    
    reg [7:0] hxxt[4];
    reg [7:0] hxxr[4];
//    reg data;
    
    reg [3:0] sec_ones = 0;
    reg [3:0] sec_tens = 0;
    reg [3:0] sec_hundereds = 0;
    reg [3:0] sec_thousands = 0;
    
    // Connection Signals
    wire rx_full, rx_empty, btn_tick,btn_tick2,btn_tick3,btn_tick4;
    wire [7:0] rec_data, rec_data1;
    

        
        
        
        
        
    
    

    
    
    always@(posedge clk_100MHz)
        if(btn_tick3) begin
            if(sec_thousands<1)
                sec_thousands++;
            else
                sec_thousands = 0;
                
        end
    
    always@(posedge clk_100MHz)
        if(btn_tick5) begin
            if(sec_hundereds<3)
                sec_hundereds++;
            else
                sec_hundereds = 0;
                
        end
        else if(btn_tick4) begin
            if(sec_hundereds>0)
                sec_hundereds--;
            else
                sec_hundereds = 3;
                
        end
    
    always@(posedge clk_100MHz)
        if(sec_thousands == 0)begin 
            sec_ones = hxxt[sec_hundereds] [3:0];
            sec_tens = hxxt[sec_hundereds] [7:4];
        end
        else begin 
            sec_ones = hxxr[sec_hundereds] [3:0];
            sec_tens = hxxr[sec_hundereds] [7:4];
        end
            
            
        
            
            
                 
            
            
    // Complete UART Core
    uart_top UART_UNIT
        (
            .clk_100MHz(clk_100MHz),
            .reset(1'b0),
            .write_uart(btn_tick2),
            .rx(rx),
            .write_data(TXBUF), //prev rec_data1 //txinput
            .rx_full(rx_full),
            .rx_empty(rx_empty),
            .read_data(RXBUF), //rxout   prev rec_data
            .tx(tx),
            .btnD(btn_tick),
            .led_control(fifotxout),
            .rx_done_tick(rx_done_tick),
            .tx_done_tick(tx_done_tick),
            .TXoldest(TXoldest)
            //.fifototx(fifotxout)
            //.tx_done(tx_done),
            //.tx_fifo_not(tx_fifo_not),
            //.tx_empt(tx_empt),
            //.in(in),
            //.in0(in0),
            //.in2(in2)
            
        );
        fifotr fifo
	(
	   .clk_100MHz(clk_100MHz),                              // FPGA clock   
	   .TXBUF_write(tx_done_tick),                    // signal start writing to FIFO
	   .RXBUF_write(rx_done_tick),                   // signal start reading from FIFO
	   .TXBUF(TXBUF),                  // data word into FIFO
	   .RXBUF(RXBUF),                  // data word out of FIFO
	   .TXoldest(TXoldest),
	   .RXdisplay(RXdisplay),
	   .hxxt(hxxt),
	   .hxxr(hxxr)
);
        
        
        
        
    //newdebounceer
//    btn_debouncer BTN_DEBOUNCER
//            (
//            .clk_100MHz(clk_100MHz),
//            .btn_in(btn),   
//            .btn_out(btn_tick)
//            );
//    btn_debouncer BTN_DEBOUNCER2
//            (
//            .clk_100MHz(clk_100MHz),
//            .btn_in(),   
//            .btn_out(btn_tick2)
//            );
//    btn_debouncer BTN_DEBOUNCER3
//            (
//            .clk_100MHz(clk_100MHz),
//            .btn_in(btnD),   
//            .btn_out(btn_tick3)
//            );

    debounce_explicit debounce(
    .clk_100MHz(clk_100MHz),
    .reset(1'b0),
    .btn(btnD),              // button input
    .db_tick(btn_tick)      // for buttons
    );
    
    debounce_explicit debounce2(
    .clk_100MHz(clk_100MHz),
    .reset(1'b0),
    .btn(btn),              // button input
    .db_tick(btn_tick2)      // for buttons
    );
    
    debounce_explicit debounce3(
    .clk_100MHz(clk_100MHz),
    .reset(1'b0),
    .btn(btnU),              // button input
    .db_tick(btn_tick3)      // for buttons
    );
    
    debounce_explicit debounce4(
    .clk_100MHz(clk_100MHz),
    .reset(1'b0),
    .btn(btnL),              // button input
    .db_tick(btn_tick4)      // for buttons
    );
    
    debounce_explicit debounce5(
    .clk_100MHz(clk_100MHz),
    .reset(1'b0),
    .btn(btnR),              // button input
    .db_tick(btn_tick5)      // for buttons
    );


    seg7_control seg7(.clk_100MHz(clk_100MHz), .reset(1'b0), .ones(sec_ones), .tens(sec_tens), .hundereds(sec_hundereds), 
                      .thousands(sec_thousands), .seg(seg), .an(an));
                      
                      
    
    
    
    //assign RXBUF = rec_data;
    
    
    // Output Logic
    assign LED = RXdisplay;              // data byte received displayed on LEDs
    
    
endmodule

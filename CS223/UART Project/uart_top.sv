`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 05.05.2024 19:39:28
// Design Name: 
// Module Name: uart_top
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


module uart_top
    #(
        parameter   DBITS = 8,          // number of data bits in a word
                    SB_TICK = 16,       // number of stop bit / oversampling ticks
                    BR_LIMIT = 52,     // baud rate generator counter limit
                    BR_BITS = 6,       // number of baud rate generator counter bits
                    FIFO_EXP = 2        // exponent for number of FIFO addresses (2^2 = 4)
    )
    (
        input clk_100MHz,               // FPGA clock
        input reset,                    // reset
        //input read_uart,                // button
        input write_uart,               // button
        input rx,                       // serial data in
        input [DBITS-1:0] write_data,   // data from Tx FIFO
        input btnD,
        output rx_full,                 // do not write data to FIFO
        output rx_empty,                // no data to read from FIFO
        output reg tx,   //not reg                   // serial data out
        output [DBITS-1:0] read_data,    // data to Rx FIFO
        output reg [DBITS-1:0] led_control,
        output rx_done_tick,
        output tx_done_tick,
        output [7:0] TXoldest
        //output [DBITS-1:0] fifototx,
        //output tx_done,
        //output reg tx_fifo_not,
        //output tx_empt,
        //output in,
        //output in0,
        //output in2
    );
    
    // Connection Signals
    wire tick;                          // sample tick from baud rate generator
    //wire rx_done_tick;                  // data word received
    //wire tx_done_tick;                  // data transmission complete
    wire tx_empty;                      // Tx FIFO has no data to transmit
    wire tx_fifo_not_empty;             // Tx FIFO contains data to transmit
    wire [DBITS-1:0] tx_fifo_out;       // from Tx FIFO to UART transmitter
    wire [DBITS-1:0] rx_data_out;       // from UART receiver to Rx FIFO
    
    
//    assign tx_done = tx_done_tick;
//    assign tx_fifo_not = tx_fifo_not_empty;
//    assign tx_empt = tx_empty;
    
//    always@(posedge clk_100MHz)
//        if(!tx_fifo_not_empty)
//            tx_fifo_not = 1'b1;
            
      always@(posedge clk_100MHz)
        if(btnD)
            led_control = write_data; 
            
    
    baud_rate_generator 
        #(
            .M(BR_LIMIT), 
            .N(BR_BITS)
         ) 
        BAUD_RATE_GEN   
        (
            .clk_100MHz(clk_100MHz), 
            .reset(reset),
            .tick(tick)
         );
    
    uart_receiver
        #(
            .DBITS(DBITS),
            .SB_TICK(SB_TICK)
         )
         UART_RX_UNIT
         (
            .clk_100MHz(clk_100MHz),
            .reset(reset),
            .rx(tx), //rx
            .sample_tick(tick),
            .data_ready(rx_done_tick),
            .data_out(read_data)   //rx_data_out
         );
    
    uart_transmitter
        #(
            .DBITS(DBITS),
            .SB_TICK(SB_TICK)
         )
         UART_TX_UNIT
         (
            .clk_100MHz(clk_100MHz),
            .reset(reset),
            .tx_start(write_uart),  //tx_fifo_not_empty
            .sample_tick(tick),
            .data_in(TXoldest),    //tx_fifo_out
            .tx_done(tx_done_tick),
            .tx(tx)
            //.in(in),
            //.in0(in0),
            //.in2(in2)
         );
         
//     fifo_generator_0 fifo0 (
//        .clk(clk_100MHz),      // input wire clk
//        .srst(reset),    // input wire srst
//        .din(rx_data_out),      // input wire [7 : 0] din
//        .wr_en(rx_done_tick),  // input wire wr_en
//        .rd_en(1'b1),  // input wire rd_en
//        .dout(read_data),    // output wire [7 : 0] dout
//        .full(rx_full),    // output wire full
//        .empty(rx_empty)  // output wire empty
//);
    
//    fifo
//        #(
//            .DATA_SIZE(DBITS),
//            .ADDR_SPACE_EXP(FIFO_EXP)
//         )
//         FIFO_RX_UNIT
//         (
//            .clk(clk_100MHz),
//            .reset(reset),
//            .write_to_fifo(rx_done_tick),
//	        .read_from_fifo(1'b1),   //read_uart
//	        .write_data_in(rx_data_out),
//	        .read_data_out(read_data), //output
//	        .empty(rx_empty),
//	        .full(rx_full)            
//	      );
	   
//    fifo
//        #(
//            .DATA_SIZE(DBITS),
//            .ADDR_SPACE_EXP(FIFO_EXP)
//         )
//         FIFO_TX_UNIT
//         (
//            .clk(clk_100MHz),
//            .reset(reset),
//            .write_to_fifo(write_uart),
//	        .read_from_fifo(tx_done_tick),
//	        .write_data_in(write_data),
//	        .read_data_out(tx_fifo_out),
//	        .empty(tx_empty),
//	        .full()                // intentionally disconnected
//	      );

	 
    
    // Signal Logic
    assign tx_fifo_not_empty = ~tx_empty;
    assign fifototx = tx_fifo_out;
endmodule

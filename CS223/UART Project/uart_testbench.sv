`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 09.05.2024 00:54:49
// Design Name: 
// Module Name: uart_testbench
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




module uart_testbench;

    // Parameters
    parameter CLK_PERIOD = 10; // Clock period in ns

    // Signals
    reg clk_100MHz;
    reg reset;
    reg rx;
    reg btn;
    reg [7:0] TXBUF;
    wire tx;
    wire [3:0] an;
    wire [0:6] seg;
    wire [7:0] LED;
    wire [7:0] fifotxout;

    // Instantiate the module under test
    uart_test uut (
        .clk_100MHz(clk_100MHz),
        .reset(reset),
        .rx(rx),
        .btn(btn),
        .TXBUF(TXBUF),
        .tx(tx),
        .an(an),
        .seg(seg),
        .LED(LED),
        .fifotxout(fifotxout)
    );

    // Clock generation
    always begin
        clk_100MHz = 0;
        #((CLK_PERIOD) / 2);
        clk_100MHz = 1;
        #((CLK_PERIOD) / 2);
    end

    // Reset generation
    initial begin
        reset = 1;
        #10;
        reset = 0;
    end

    // Stimulus
    initial begin
        // Initialize inputs
        rx = 0;
        btn = 0;
        TXBUF = 8'h00;

        // Stimulus generation
        #20;
        // Simulate receiving data on RX line
        rx = 1;
        #50;
        rx = 0;
        #100;
        // Simulate button press to trigger FIFO operation
        btn = 1;
        #10;
        btn = 0;
        #100;
        // Provide data to be transmitted
        TXBUF = 8'hFF;
        #100;

        // Add more stimulus as needed

        // Finish simulation
        #100;
        $finish;
    end

    // Monitor
    always @(posedge clk_100MHz) begin
        // Monitor the outputs
        $display("tx=%h, an=%h, seg=%h, LED=%h, fifotxout=%h", tx, an, seg, LED, fifotxout);
    end

endmodule



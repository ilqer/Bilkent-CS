`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 05.05.2024 19:31:21
// Design Name: 
// Module Name: baud_rate_generator
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


module baud_rate_generator
    #(              // 115200 baud
        parameter   N = 6,     // number of counter bits
                    M = 52     // counter limit value
    )
    (
        input clk_100MHz,       // basys 3 clock
        input reset,            // reset
        output tick             // sample tick
    );
    
    // Counter Register
    reg [N-1:0] counter;        // counter value
    wire [N-1:0] next;          // next counter value
    
    // Register Logic
    always @(posedge clk_100MHz, posedge reset)
        if(reset)
            counter <= 0;
        else
            counter <= next;
            
    // Next Counter Value Logic
    assign next = (counter == (M-1)) ? 0 : counter + 1;
    
    // Output Logic
    assign tick = (counter == (M-1)) ? 1'b1 : 1'b0;
       
endmodule

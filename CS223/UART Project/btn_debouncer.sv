`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 09.05.2024 00:59:47
// Design Name: 
// Module Name: btn_debouncer
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


module btn_debouncer(
    input clk_100MHz,
    input btn_in,
    output reg btn_out
    );
    
    
    reg temp1, temp2, temp3;
    
    always @(posedge clk_100MHz) begin
        temp1 <= btn_in;
        temp2 <= temp1;
        temp3 <= temp2;
    end
    
    assign btn_out = temp3;
    
    
    
    
//    reg [25:0] counter;
//    reg temp;
    
//    always @(posedge clk_100MHz) begin
//        if(btn_in) begin 
//            temp <= btn_in;
            
//            if(counter == 149_999_999) begin
//                counter <= 0;
//                btn_out <= temp;
//                temp <= 1'b0;
//            end
//            else
//            btn_out <= 1'b0;
//            counter <= counter + 1;
//        end
        
//        end    
    
    
endmodule

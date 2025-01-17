`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 06.05.2024 09:42:19
// Design Name: 
// Module Name: seg7_control
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


module seg7_control(
    input clk_100MHz,
    input reset,
    input [3:0] ones,
    input [3:0] tens,
    input [3:0] hundereds,
    input [3:0] thousands,
    output reg [0:6] seg,
    output reg [3:0] an
    );
    
    // Parameters for segment values
    parameter NULL  = 7'b111_1111;  // Turn off all segments
    parameter ZERO  = 7'b000_0001;  // 0
    parameter ONE   = 7'b100_1111;  // 1
    parameter TWO   = 7'b001_0010;  // 2 
    parameter THREE = 7'b000_0110;  // 3
    parameter FOUR  = 7'b100_1100;  // 4
    parameter FIVE  = 7'b010_0100;  // 5
    parameter SIX   = 7'b010_0000;  // 6
    parameter SEVEN = 7'b000_1111;  // 7
    parameter EIGHT = 7'b000_0000;  // 8
    parameter NINE  = 7'b000_0100;  // 9
    parameter F = 7'b011_1000; //F;
    parameter E = 7'b011_0000; //E;
    parameter d = 7'b100_0010; //d;
    parameter c = 7'b111_0010; //c;
    parameter b = 7'b110_0000; //b;
    parameter A = 7'b000_1000; //A;
    parameter t = 7'b111_1000; //t;
    parameter r = 7'b111_1010; //r;
    
    
    // To select each anode in turn
        reg [1:0] anode_select;
        reg [16:0] anode_timer;
        
        always @(posedge clk_100MHz or posedge reset) begin
            if(reset) begin
                anode_select <= 0;
                anode_timer <= 0; 
            end
            else
                if(anode_timer == 99_999) begin
                    anode_timer <= 0;
                    anode_select <=  anode_select + 1;
                end
                else
                    anode_timer <=  anode_timer + 1;
        end
        
        always @(anode_select) begin
            case(anode_select) 
                2'b00 : an = 4'b0111;
                2'b01 : an = 4'b1011;
                2'b10 : an = 4'b1101;
                2'b11 : an = 4'b1110;
            endcase
        end
    
    // To drive the segments
    always @*
        case(anode_select)
            2'b00 : begin       
                        case(thousands)
                            4'b0000 : seg = t;
                            4'b0001 : seg = r;
                            
                            
                        endcase
                    end
                    
            2'b01 : begin       
                        case(hundereds)
                            4'b0000 : seg = ZERO;
                            4'b0001 : seg = ONE;
                            4'b0010 : seg = TWO;
                            4'b0011 : seg = THREE;
                        endcase
                    end
                    
            2'b10 : begin       
                        case(tens)
                            4'b0000 : seg = ZERO;
                            4'b0001 : seg = ONE;
                            4'b0010 : seg = TWO;
                            4'b0011 : seg = THREE;
                            4'b0100 : seg = FOUR;
                            4'b0101 : seg = FIVE;
                            4'b0110 : seg = SIX;
                            4'b0111 : seg = SEVEN;
                            4'b1000 : seg = EIGHT;
                            4'b1001 : seg = NINE;
                            4'b1111 : seg = F;
                            4'b1110 : seg = E;
                            4'b1101 : seg = d;
                            4'b1100 : seg = c;
                            4'b1011 : seg = b;
                            4'b1010 : seg = A;
                        endcase
                    end
                    
            2'b11 : begin       
                        case(ones)
                            4'b0000 : seg = ZERO;
                            4'b0001 : seg = ONE;
                            4'b0010 : seg = TWO;
                            4'b0011 : seg = THREE;
                            4'b0100 : seg = FOUR;
                            4'b0101 : seg = FIVE;
                            4'b0110 : seg = SIX;
                            4'b0111 : seg = SEVEN;
                            4'b1000 : seg = EIGHT;
                            4'b1001 : seg = NINE;
                            4'b1111 : seg = F;
                            4'b1110 : seg = E;
                            4'b1101 : seg = d;
                            4'b1100 : seg = c;
                            4'b1011 : seg = b;
                            4'b1010 : seg = A;
                        endcase
                    end
        endcase
  
endmodule

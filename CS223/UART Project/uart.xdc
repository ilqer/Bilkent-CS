# Clock signal
set_property PACKAGE_PIN W5 [get_ports clk_100MHz]
set_property IOSTANDARD LVCMOS33 [get_ports clk_100MHz]
create_clock -period 10.000 -name sys_clk_pin -waveform {0.000 5.000} -add [get_ports clk_100MHz]

# LEDs
set_property PACKAGE_PIN U16 [get_ports {fifotxout[0]}]
set_property IOSTANDARD LVCMOS33 [get_ports {fifotxout[0]}]
set_property PACKAGE_PIN E19 [get_ports {fifotxout[1]}]
set_property IOSTANDARD LVCMOS33 [get_ports {fifotxout[1]}]
set_property PACKAGE_PIN U19 [get_ports {fifotxout[2]}]
set_property IOSTANDARD LVCMOS33 [get_ports {fifotxout[2]}]
set_property PACKAGE_PIN V19 [get_ports {fifotxout[3]}]
set_property IOSTANDARD LVCMOS33 [get_ports {fifotxout[3]}]
set_property PACKAGE_PIN W18 [get_ports {fifotxout[4]}]
set_property IOSTANDARD LVCMOS33 [get_ports {fifotxout[4]}]
set_property PACKAGE_PIN U15 [get_ports {fifotxout[5]}]
set_property IOSTANDARD LVCMOS33 [get_ports {fifotxout[5]}]
set_property PACKAGE_PIN U14 [get_ports {fifotxout[6]}]
set_property IOSTANDARD LVCMOS33 [get_ports {fifotxout[6]}]
set_property PACKAGE_PIN V14 [get_ports {fifotxout[7]}]
set_property IOSTANDARD LVCMOS33 [get_ports {fifotxout[7]}]

set_property PACKAGE_PIN V13 [get_ports {LED[0]}]  	 	 	 	 
 	set_property IOSTANDARD LVCMOS33 [get_ports {LED[0]}] 
set_property PACKAGE_PIN V3 [get_ports {LED[1]}] 	 	 	 	 	 
 	set_property IOSTANDARD LVCMOS33 [get_ports {LED[1]}] 
set_property PACKAGE_PIN W3 [get_ports {LED[2]}]  	 	 	 	 
 	set_property IOSTANDARD LVCMOS33 [get_ports {LED[2]}] 
set_property PACKAGE_PIN U3 [get_ports {LED[3]}]  	 	 	 	 
 	set_property IOSTANDARD LVCMOS33 [get_ports {LED[3]}] 
set_property PACKAGE_PIN P3 [get_ports {LED[4]}]  	 	 	 	 
 	set_property IOSTANDARD LVCMOS33 [get_ports {LED[4]}] 
set_property PACKAGE_PIN N3 [get_ports {LED[5]}]  	 	 	 	 
 	set_property IOSTANDARD LVCMOS33 [get_ports {LED[5]}] 
set_property PACKAGE_PIN P1 [get_ports {LED[6]}]  	 	 	 	 
 	set_property IOSTANDARD LVCMOS33 [get_ports {LED[6]}] 
set_property PACKAGE_PIN L1 [get_ports {LED[7]}]  	 	 	 	  			
	set_property IOSTANDARD LVCMOS33 [get_ports {LED[7]}] 


# Switches
set_property PACKAGE_PIN V17 [get_ports {TXBUF[0]}]
set_property IOSTANDARD LVCMOS33 [get_ports {TXBUF[0]}]
set_property PACKAGE_PIN V16 [get_ports {TXBUF[1]}]
set_property IOSTANDARD LVCMOS33 [get_ports {TXBUF[1]}]
set_property PACKAGE_PIN W16 [get_ports {TXBUF[2]}]
set_property IOSTANDARD LVCMOS33 [get_ports {TXBUF[2]}]
set_property PACKAGE_PIN W17 [get_ports {TXBUF[3]}]
set_property IOSTANDARD LVCMOS33 [get_ports {TXBUF[3]}]
set_property PACKAGE_PIN W15 [get_ports {TXBUF[4]}]
set_property IOSTANDARD LVCMOS33 [get_ports {TXBUF[4]}]
set_property PACKAGE_PIN V15 [get_ports {TXBUF[5]}]
set_property IOSTANDARD LVCMOS33 [get_ports {TXBUF[5]}]
set_property PACKAGE_PIN W14 [get_ports {TXBUF[6]}]
set_property IOSTANDARD LVCMOS33 [get_ports {TXBUF[6]}]
set_property PACKAGE_PIN W13 [get_ports {TXBUF[7]}]
set_property IOSTANDARD LVCMOS33 [get_ports {TXBUF[7]}]


#7 segment display
set_property PACKAGE_PIN W7 [get_ports {seg[0]}]
set_property IOSTANDARD LVCMOS33 [get_ports {seg[0]}]
set_property PACKAGE_PIN W6 [get_ports {seg[1]}]
set_property IOSTANDARD LVCMOS33 [get_ports {seg[1]}]
set_property PACKAGE_PIN U8 [get_ports {seg[2]}]
set_property IOSTANDARD LVCMOS33 [get_ports {seg[2]}]
set_property PACKAGE_PIN V8 [get_ports {seg[3]}]
set_property IOSTANDARD LVCMOS33 [get_ports {seg[3]}]
set_property PACKAGE_PIN U5 [get_ports {seg[4]}]
set_property IOSTANDARD LVCMOS33 [get_ports {seg[4]}]
set_property PACKAGE_PIN V5 [get_ports {seg[5]}]
set_property IOSTANDARD LVCMOS33 [get_ports {seg[5]}]
set_property PACKAGE_PIN U7 [get_ports {seg[6]}]
set_property IOSTANDARD LVCMOS33 [get_ports {seg[6]}]

set_property PACKAGE_PIN U2 [get_ports {an[0]}]
set_property IOSTANDARD LVCMOS33 [get_ports {an[0]}]
set_property PACKAGE_PIN U4 [get_ports {an[1]}]
set_property IOSTANDARD LVCMOS33 [get_ports {an[1]}]
set_property PACKAGE_PIN V4 [get_ports {an[2]}]
set_property IOSTANDARD LVCMOS33 [get_ports {an[2]}]
set_property PACKAGE_PIN W4 [get_ports {an[3]}]
set_property IOSTANDARD LVCMOS33 [get_ports {an[3]}]

##Buttons
## btnC
set_property PACKAGE_PIN U18 [get_ports btn]
set_property IOSTANDARD LVCMOS33 [get_ports btn]

##btnD
set_property PACKAGE_PIN U17 [get_ports btnD]
set_property IOSTANDARD LVCMOS33 [get_ports btnD]

set_property PACKAGE_PIN T18 [get_ports btnU]
set_property IOSTANDARD LVCMOS33 [get_ports btnU]
set_property PACKAGE_PIN W19 [get_ports btnL]
set_property IOSTANDARD LVCMOS33 [get_ports btnL]


## btnR
set_property PACKAGE_PIN T17 [get_ports btnR]
set_property IOSTANDARD LVCMOS33 [get_ports btnR]

##USB-RS232 Interface
set_property PACKAGE_PIN B18 [get_ports rx]
set_property IOSTANDARD LVCMOS33 [get_ports rx]
set_property PACKAGE_PIN A18 [get_ports tx]
set_property IOSTANDARD LVCMOS33 [get_ports tx]




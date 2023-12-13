#Create a ns simulator
set ns [new Simulator]

#Setup topography object
set topo [new Topography]
$topo load_flatgrid 1500 1500

#Open the NS trace file
set tracefile [open pr6.tr w]
$ns trace-all $tracefile

#Open the NAM trace file
set namfile [open pr6.nam w]
$ns namtrace-all $namfile
$ns namtrace-all-wireless $namfile 1500 1500


$ns node-config -adhocRouting DSDV \
$ns node-config -llType LL \
$ns node-config -macType Mac/802_11 \
$ns node-config -ifqType Queue/DropTail \
$ns node-config -ifqLen 20 \
$ns node-config -phyType Phy/WirelessPhy \
$ns node-config -channelType Channel/WirelessChannel \
$ns node-config -propType Propagation/TwoRayGround \
$ns node-config -antType Antenna/OmniAntenna \
$ns node-config -topoInstance $topo \
$ns node-config -agentTrace ON \
$ns node-config -routerTrace ON


create-god 6
#Create 6 nodes
set n0 [$ns node]
$n0 set X_ 630
$n0 set Y_ 501
$n0 set Z_ 0.0
$ns initial_node_pos $n0 20
set n1 [$ns node]
$n1 set X_ 454
$n1 set Y_ 340
$n1 set Z_ 0.0
$ns initial_node_pos $n1 20
set n2 [$ns node]
$n2 set X_ 785
$n2 set Y_ 326
$n2 set Z_ 0.0
$ns initial_node_pos $n2 20
set n3 [$ns node]
$n3 set X_ 270
$n3 set Y_ 190
$n3 set Z_ 0.0
$ns initial_node_pos $n3 20
set n4 [$ns node]
$n4 set X_ 539
$n4 set Y_ 131
$n4 set Z_ 0.0
$ns initial_node_pos $n4 20
set n5 [$ns node]
$n5 set X_ 964
$n5 set Y_ 177
$n5 set Z_ 0.0
$ns initial_node_pos $n5 20


#Setup a UDP connection
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
set null1 [new Agent/Null]
$ns attach-agent $n4 $null1
$ns connect $udp0 $null1
$udp0 set packetSize_ 1500

#Setup a TCP connection
set tcp0 [new Agent/TCP]
$ns attach-agent $n3 $tcp0
set sink1 [new Agent/TCPSink]
$ns attach-agent $n5 $sink1
$ns connect $tcp0 $sink1


#Setup a CBR Application over UDP connection
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0
$cbr0 set packetSize_ 1000
$cbr0 set rate_ 1.743Mb
$cbr0 set random_ null

#Setup a FTP Application over TCP connection
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0


#Define a 'finish' procedure
proc finish {} {
global ns tracefile namfile
$ns flush-trace
close $tracefile
close $namfile
exec nam pr6.nam &
exec echo "Number of packets dropped is : " &
exec grep -c "^D" pr6.tr &
exec awk -f pr6.awk pr6.tr &
exit 0
}

$ns at 1.0 "$cbr0 start"
$ns at 2.0 "$ftp0 start"
$ns at 18.0 "$ftp0 stop"
$ns at 20.0 "$cbr0 stop"
$ns at 20.0 "finish"
$ns at 7 "$n4 setdest 100 60 20"
$ns at 10 "$n4 setdest 700 300 20"
$ns at 15 "$n4 setdest 900 200 20"
$ns run

******* awk file******
BEGIN{
count1=0
count2=0
pack1=0
pack2=0
time1=0
time2=0
}
{
if($1=="r" && $3=="_1_" && $4=="RTR")
{
count1++
pack1=pack1+$8
time1=$2
}
if($1=="r" && $3=="_2_" && $4=="RTR")
{
count2++
pack2=pack2+$8
time2=$2
}
}
END{
printf("The Throughput from n0 to n1: %f Mbps \n", ((count1*pack1*8)/(time1*1000000)));
printf("The Throughput from n1 to n2: %f Mbps \n", ((count2*pack2*8)/(time2*1000000)));
}

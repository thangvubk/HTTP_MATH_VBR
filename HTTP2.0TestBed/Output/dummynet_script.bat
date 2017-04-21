@echo on
@set CYGWIN=nodosfilewarning
@ipfw -q flush
@ipfw -q pipe flush
ipfw add pipe 3 ip from me to myserver
ipfw add pipe 3 ip from myserver to me

ipfw pipe 3 config bw 3804Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1634Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1749Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2109Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2522Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2211Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1797Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1683Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1097Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 423Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 274Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 327Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1805Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3545Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3617Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3570Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3686Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3729Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3722Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3511Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3613Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3866Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3927Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3595Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3233Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2596Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2198Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2459Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2295Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2838Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3259Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2848Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3044Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3127Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2525Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2307Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2442Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2291Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1500Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1325Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1628Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1476Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1460Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1574Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1501Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1400Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1355Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1354Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1395Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1243Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1254Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1839Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2301Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1935Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1787Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2255Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1990Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1519Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1627Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2090Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2612Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2705Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3127Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3244Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2676Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2757Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3179Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3313Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2978Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2828Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3123Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3009Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3278Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3351Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3111Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3288Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3623Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3888Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3930Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4254Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4210Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4170Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4693Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4480Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4363Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4406Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4050Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4059Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4013Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3710Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2880Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2033Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2896Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3574Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3429Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4365Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4487Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3659Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2117Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 571Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2736Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5336Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5026Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4739Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4461Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3798Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4403Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5292Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4556Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4623Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5365Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5062Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4797Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4814Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4967Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4956Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4535Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4549Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4644Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3161Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2595Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3541Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3756Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2566Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1624Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2219Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2601Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2727Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2659Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2564Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3099Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3491Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3324Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3123Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3519Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4109Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3897Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3294Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3599Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4404Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4121Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2446Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1677Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2412Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2884Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3568Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3790Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3805Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3860Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3801Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3989Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3993Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4511Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4648Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4668Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4475Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3881Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4130Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4690Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4721Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4305Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4706Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4991Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3820Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2471Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2917Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3788Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3876Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3908Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3596Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3376Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3309Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3027Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2351Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1838Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1788Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1640Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1513Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2274Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2953Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2573Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2122Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2356Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2413Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3003Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3912Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4304Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4268Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3865Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4261Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3861Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2928Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3165Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3395Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3752Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4392Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4743Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4645Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3961Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3804Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3732Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3608Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3371Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3107Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3574Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3884Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3794Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3946Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4204Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4033Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3857Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3394Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2490Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2574Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3372Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3376Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3608Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3698Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3135Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2721Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3029Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3504Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3697Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3837Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3137Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2289Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2304Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2436Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2488Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2677Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2941Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3399Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3612Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3555Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3599Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3832Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3741Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3052Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1795Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1800Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3038Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3154Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1759Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 869Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 921Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1558Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2455Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3019Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3220Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3575Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4304Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4590Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4624Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4903Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4787Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3161Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2693Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3637Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3845Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3583Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3429Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3735Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4109Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3925Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3700Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3456Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2629Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1951Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1415Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1249Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1067Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1355Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2559Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3171Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3650Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4422Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5055Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5179Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5025Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4935Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4664Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4559Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4390Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3895Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3154Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1918Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1582Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3661Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4563Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3307Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2656Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2257Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1621Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1799Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2464Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2580Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2515Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2156Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1473Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 816Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 838Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1573Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2031Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2219Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2647Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2968Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2832Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2466Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2356Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2535Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2582Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2541Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2672Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1375Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1201Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1645Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1620Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2551Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2270Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2578Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1758Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1713Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3219Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2866Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2470Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2417Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2450Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2286Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2018Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2001Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2137Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2073Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2037Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2061Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1975Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1868Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1954Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2162Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2088Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1722Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1722Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2114Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2104Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1671Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1705Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2147Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2330Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2349Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2359Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2463Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2565Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2857Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2995Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2985Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3483Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3601Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3787Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4028Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3810Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3817Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3872Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4143Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4354Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4900Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5099Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4574Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4340Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4109Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3960Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4012Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4040Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4120Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4577Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5250Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5303Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5075Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4843Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4999Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5303Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5308Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5362Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4998Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4846Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5283Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5519Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5054Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5066Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5242Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4380Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3171Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3600Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4630Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4802Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5261Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5386Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5209Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5377Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5104Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4952Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5363Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5353Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5347Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5401Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5479Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5248Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5059Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4401Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4392Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5006Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4932Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5028Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5215Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5154Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5212Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5455Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5417Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5525Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5496Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5496Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5549Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5265Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4135Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3273Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3361Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3655Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3913Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3956Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4011Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4186Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4287Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4740Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4770Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4495Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4929Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4723Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4286Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4088Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3553Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3221Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3145Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3397Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3668Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3797Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3990Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1786Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1986Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2150Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1810Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1712Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1715Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1896Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1916Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1831Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1786Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1597Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1200Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1567Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3592Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3649Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1781Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1273Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2785Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3783Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4037Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4022Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4006Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4154Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4215Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4057Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4242Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4242Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4067Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4048Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3972Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3711Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3783Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3839Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3652Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3822Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3863Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3841Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3806Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3667Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3543Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3024Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2231Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2238Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3345Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4165Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4186Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3571Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3066Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2993Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2701Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1950Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1765Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1883Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1157Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 998Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2425Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3183Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2930Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3189Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3742Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3906Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3793Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3172Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3088Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3331Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3321Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3852Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4483Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4801Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5198Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4930Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4533Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4901Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4958Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4640Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4629Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4742Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4583Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4880Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5123Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5030Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4733Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4336Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4586Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4506Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4844Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4763Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4345Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4690Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4488Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4503Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3998Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3043Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3282Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3093Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1821Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 922Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 1606Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3605Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4633Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3864Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3144Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4252Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5575Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5551Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5388Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5417Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5201Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5069Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4859Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4073Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3889Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4357Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3927Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3259Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3311Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3457Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3540Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3688Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3494Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3673Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3860Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3317Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2942Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2967Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3151Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3242Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3038Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2524Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 2494Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3115Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3367Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3133Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3297Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3367Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3617Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4598Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5084Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5251Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5572Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5581Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4856Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4917Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5625Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 5170Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4676Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4185Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4337Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4746Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4580Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4726Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 4111Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3023Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3216Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL
ipfw pipe 3 config bw 3055Kbit/s delay 50ms
ping 1.1.1.1 -n 1 -w 1000 >NUL

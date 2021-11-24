<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  /* //높이를 지정해줘야 세로 가운ㄷ데 정렬 가능해요~  */
  margin: 0;
  background-color: #000;
  font-family: "Black Han Sans", sans-serif;
  /* //미리준비해둔 폰트 !  */
}
p {
  position: relative;
  margin: 0;
  color: #fff;
  font-size: 5rem;
}
p::before,
p::after {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  content: attr(data-text);
}
p::before {
  color: purple;
  z-index: -1;
  animation: glitch01 0.3s ease infinite;
}
p::after {
  color: cyan;
  z-index: -2;
  animation: glitch02 0.3s ease infinite reverse;
}
@keyframes glitch01 {
  0% {
    transform: translate(-2px, 2px);
  }
  25% {
    transform: translate(-4px, 4px);
  }
  50% {
    transform: translate(2px, 2px);
  }
  75% {
    transform: translate(2px, -4px);
  }
  100% {
    transform: translate(-2px, 2px);
  }
}
@keyframes glitch02 {
  0% {
    transform: translate(-2px, 2px);
  }
  25% {
    transform: translate(-4px, 4px);
  }
  50% {
    transform: translate(2px, 2px);
  }
  75% {
    transform: translate(2px, -4px);
  }
  100% {
    transform: translate(-2px, 2px);
  }
}


</style>
</head>
<body>
<p data-text="예약이 완료되었습니다.">예약이 완료되었습니다.</p>
</body>
</html>
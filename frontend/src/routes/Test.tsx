import { useNavigate } from 'react-router-dom';
import { useLoginInfoQuery } from '../hooks/useLoginInfoQuery';
import { refreshPage } from '../utils/movePage';
import { useUserInfo } from '../hooks/useUserInfo';
import { doLogout } from '../api/auth/authAPI';
import { MS } from 'styles';

export default function Test() {
  const navigate = useNavigate();
  const { isLogin, accessToken } = useLoginInfoQuery();
  const userInfoQuery = useUserInfo();

  return (
    <div className={MS.container}>
      <button
        onClick={() => {
          navigate('/register');
        }}>
        회원가입
      </button>
      <button
        onClick={() => {
          navigate('/login');
        }}>
        로그인
      </button>
      <p>
        {isLogin ? '로그인쿠키 있음' : '로그인쿠키 없음'}, {accessToken}
      </p>
      <p>로그인 정보: {JSON.stringify(userInfoQuery.data)}</p>
      <button
        onClick={() => {
          doLogout();
          refreshPage(navigate);
        }}>
        로그아웃
      </button>
    </div>
  );
}

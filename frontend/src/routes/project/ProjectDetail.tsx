import { useNavigate, useParams } from 'react-router-dom';
import { BS, MS } from 'styles';
import S from './ProjectDetail.module.scss';
import { useProjectDetailInfoQuery } from 'hooks/useProjectDetailInfoQuery';
import { useProjectMemberQuery } from 'hooks/useProjectMemberQuery';

export default function ProjectDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { basicInfoQuery, charterQuery, timelinesQuery } = useProjectDetailInfoQuery(Number(id));
  const memberQuery = useProjectMemberQuery(Number(id));

  return (
    <div className={MS.container}>
      <div className={S.doubleContentDiv}>
        <div className={MS.content}>
          <div className={MS.contentTitle}>
            <p>프로젝트 정보</p>
          </div>
          <div className={MS.contentBox}>
            <p>{JSON.stringify(basicInfoQuery.data)}</p>
            <p>{JSON.stringify(charterQuery.data)}</p>
          </div>
        </div>
        <div className={MS.content}>
          <div className={MS.contentTitle}>
            <p>타임라인</p>
          </div>
          <div className={MS.contentBox}>
            <p>ID: {id} 타임라인 정보가 들어감</p>
            {/* <p>{JSON.stringify(timelinesQuery.data)}</p> */}
          </div>
        </div>
      </div>
      <div className={MS.content}>
        <div className={MS.contentTitle}>
          <p>프로젝트 인원 수정</p>
        </div>
        <div className={MS.contentBox}>
          <p>{JSON.stringify(memberQuery.data)}</p>
          <button className={BS.WhiteBtn} onClick={() => navigate(`/project/${id}/member`)}>
            인원 수정
          </button>
        </div>
      </div>
    </div>
  );
}
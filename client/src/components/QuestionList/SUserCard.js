import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { getRandomUserImgUrl } from '../../util/util';

const SUserCardMinimal = styled.div`
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 0;
  flex-wrap: wrap;
  margin-left: auto;
  justify-content: flex-end;
  grid-template-columns: auto 1fr;
  line-height: 1;
`;

const SUserCardAvatar = styled.div`
  display: inline-block;
  position: relative;
  width: 16px;
  height: 16px;
  border-radius: 3px;
  background-color: #ffffff;
  background-repeat: no-repeat;
  background-size: 100%;
  vertical-align: bottom;
  padding: 0;
  overflow: hidden;

  img {
    width: 16px;
    height: 16px;
    border-radius: 3px;
    display: block;
    margin: 0 auto;
  }
`;

const SUserCardInfo = styled.div`
  display: flex;
  gap: 4px;
  flex-direction: row;
  align-items: center;
`;

const SUserCardLinkWrapper = styled.div`
  white-space: nowrap;
  display: flex !important;
  margin: calc(4px / 2 * -1);
  min-width: 0;
  font-size: 12px;
  align-items: center;
  flex-wrap: wrap;
  overflow-wrap: break-word;
`;

const SUserCardLink = styled(Link)`
  margin: calc(4px / 2);
`;

const SUserCardAwards = styled.ul`
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
  align-items: center;
  gap: 6px;
`;

const SUserCardRep = styled.li`
  color: #525960;
  font-size: 12px;
  font-weight: 700;
`;

const SUserCardIime = styled.time`
  white-space: nowrap;
  grid-column: 1 / 3;
  grid-row: 1 / 2;
  color: #6a737c;
  font-size: 12px;
`;

const SLinkMuted = styled(Link)`
  color: #6a737c;
  :hover,
  :active {
    color: #525960;
  }
`;

const RelativetimeSpan = styled.span`
  text-decoration: none;
`;

const SUserCard = ({ question }) => {
  const {
    actionUser: { nickName, email },
    questionId,
    actionTime,
  } = question;

  const [userImgUrl, setUserImgUrl] = useState('');

  useEffect(() => {
    setUserImgUrl(getRandomUserImgUrl(email));
  }, [question]);

  return (
    <SUserCardMinimal>
      <div aria-live="polite">
        <Link to="#">
          <SUserCardAvatar>
            <img src={userImgUrl} alt={`${nickName}'s user avatar`} />
          </SUserCardAvatar>
        </Link>
      </div>

      <SUserCardInfo>
        <SUserCardLinkWrapper>
          <SUserCardLink to="#">{nickName}</SUserCardLink>
        </SUserCardLinkWrapper>
        <SUserCardAwards>
          <SUserCardRep>
            <span title="reputation score" dir="ltr">
              {/* 숫자 k,m,b */}
              15
            </span>
          </SUserCardRep>
        </SUserCardAwards>
      </SUserCardInfo>

      <SUserCardIime>
        <SLinkMuted to={`/questions/${questionId}`}>
          asked{' '}
          <RelativetimeSpan title="2022-10-25 05:04:43Z">
            {new Date(actionTime).toISOString()}
            {/* 24 secs ago */}
          </RelativetimeSpan>
        </SLinkMuted>
      </SUserCardIime>
    </SUserCardMinimal>
  );
};

export default SUserCard;

import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { getYearDiff, getMonthDiff, getDateDiff } from '../../util/DateDiff';

const QuestionTitleContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
`;

const TitleInfo = styled.h1`
  display: flex;
  font-size: 27px;
  font-weight: 400;
  text-align: left;
  flex-grow: 1;
  justify-content: space-between;
  color: #3b4045;
  margin: 0;
  div {
    white-space: normal;
  }
`;

const AskQustionBtn = styled.button`
  background: #0995ff;
  color: #ffffff;
  font-weight: 400;
  border: 1px solid #0995ff;
  border-radius: 4px;
  box-shadow: inset 0 1px 0 0 #ffffff;
  width: 100px;
  height: 37px;
  font-size: 13px;
  &:hover {
    cursor: pointer;
    background-color: #0063bf;
  }
`;

const StatInfo = styled.div`
  display: flex;
  align-items: center;
  flex-grow: 1;
  height: 34px;
  padding-bottom: 8px;
  margin-bottom: 16px;
  font-size: 13px;
  border-bottom: 1px solid #e3e6e8;
  .name {
    color: #6a737c;
    margin-right: 5px;
  }
  .stat {
    color: #232629;
    margin-right: 16px;
  }
`;

const QuestionTitle = () => {
  const state = useSelector((state) => state.questionReducer);
  const { data } = state.data;
  const title = data === undefined ? '' : data.title;
  const views = data === undefined ? 0 : data.view;
  const createdAt = data === undefined ? '' : data.createdAt;
  const modifiedAt = data === undefined ? '' : data.modifiedAt;

  const yearDiff = getYearDiff(createdAt);
  const monthDiff = getMonthDiff(createdAt);
  const dateDiff = getDateDiff(createdAt);

  const MyearDiff = getYearDiff(modifiedAt);
  const MmonthDiff = getMonthDiff(modifiedAt);
  const MdateDiff = getDateDiff(modifiedAt);

  return (
    <QuestionTitleContainer>
      <TitleInfo>
        <div>{title}</div>
        <div>
          <Link to="/questions/ask">
            <AskQustionBtn>Ask Question</AskQustionBtn>
          </Link>
        </div>
      </TitleInfo>
      <StatInfo>
        <div className="name">Asked</div>
        <div className="stat">
          {dateDiff === 0
            ? 'today'
            : `${yearDiff} years, ${monthDiff} months ago`}
        </div>
        <div className="name">Modified</div>
        <div className="stat">
          {MdateDiff === 0
            ? 'today'
            : `${MyearDiff} years, ${MmonthDiff} months ago`}
        </div>
        <div className="name">Viewd</div>
        <div className="stat">{views} times</div>
      </StatInfo>
    </QuestionTitleContainer>
  );
};

export default QuestionTitle;

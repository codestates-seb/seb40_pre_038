import styled from 'styled-components';

const AnswersInfoContainer = styled.div`
  width: 100%;
  height: 33px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
  .wrapper-right {
    display: flex;
  }
`;

const AnswersCnt = styled.h2`
  font-size: 19px;
  margin: 0;
  color: #232629;
`;

const AnswerSortedby = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  margin-right: 3px;
  .sortedby {
    font-size: 12px;
    color: #232629;
  }
  .reset {
    font-size: 11px;
    color: #0074cc;
    &:hover {
      color: #0a95ff;
      cursor: pointer;
    }
  }
`;

const AnswerSortSelect = styled.select`
  border: 1px solid #babfc4;
  border-radius: 3px;
  outline: none;
  color: #0c0d0e;
`;

const AnswersInfo = ({ answersCnt = 0 }) => {
  return (
    <AnswersInfoContainer>
      <div className="wrapper-left">
        <AnswersCnt>{answersCnt} Answers</AnswersCnt>
      </div>
      <div className="wrapper-right">
        <AnswerSortedby>
          <div className="sortedby">Sorted by:</div>
          <div className="reset">Reset to default</div>
        </AnswerSortedby>
        <AnswerSortSelect defaultValue={'trending'}>
          <option value="scoredesc">Highest score (default)</option>
          <option value="trending">Trending (recent votes count more)</option>
          <option value="modifieddesc">Date modified (newest first)</option>
          <option value="createdasc">Date created (oldest first)</option>
        </AnswerSortSelect>
      </div>
    </AnswersInfoContainer>
  );
};

export default AnswersInfo;

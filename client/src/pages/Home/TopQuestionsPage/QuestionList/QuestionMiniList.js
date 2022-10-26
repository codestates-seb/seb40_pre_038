import SummaryStats from './SummaryStats';
import SummaryContent from './SummaryContent';
import styled from 'styled-components';

const QuestionMiniList = () => {
  return (
    <QuestionSummary>
      <SummaryStats />
      <SummaryContent />
    </QuestionSummary>
  );
};

export default QuestionMiniList;

const QuestionSummary = styled.div`
  position: relative;
  display: flex;
  border-bottom: 1px solid #e3e6e8;
  padding: 16px;

  :last-child {
    border-bottom-width: 0;
  }

  @media (max-width: 980px) {
    flex-direction: column;
  }
`;

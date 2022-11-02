import SummaryStats from './SummaryStats';
import SummaryContent from './SummaryContent';
import styled from 'styled-components';

const QuestionSummary = styled.div`
  position: relative;
  display: flex;
  border-bottom: 1px solid #e3e6e8;
  padding: 16px;

  &.question-mini-list:last-child {
    border-bottom-width: 0;
  }

  @media (max-width: 980px) {
    flex-direction: column;
  }
`;

const QuestionMiniList = ({ className }) => {
  console.log('className', className);
  return (
    <QuestionSummary className={className}>
      <SummaryStats />
      <SummaryContent className={className} />
    </QuestionSummary>
  );
};

export default QuestionMiniList;

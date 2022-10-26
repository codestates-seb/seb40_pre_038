import styled from 'styled-components';

const SummaryStats = () => {
  return (
    <SummaryStatsWrapper>
      <SummaryStatsItem className="emphasized" title="Score of 0">
        <SummaryStatsItemNumber>0</SummaryStatsItemNumber>
        <SummaryStatsItemUnit>votes</SummaryStatsItemUnit>
      </SummaryStatsItem>
      <SummaryStatsItem title="0 answers">
        <SummaryStatsItemNumber>0</SummaryStatsItemNumber>
        <SummaryStatsItemUnit>answers</SummaryStatsItemUnit>
      </SummaryStatsItem>
      <SummaryStatsItem title="3 views">
        <SummaryStatsItemNumber>3</SummaryStatsItemNumber>
        <SummaryStatsItemUnit>views</SummaryStatsItemUnit>
      </SummaryStatsItem>
    </SummaryStatsWrapper>
  );
};

export default SummaryStats;

const SummaryStatsWrapper = styled.div`
  gap: 6px;
  margin-right: 16px;
  margin-bottom: 4px;
  width: calc(96px + 12px);
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
  flex-wrap: wrap;
  align-items: flex-end;
  font-size: 13px;
  color: #6a737c;

  @media (max-width: 980px) {
    flex-direction: row;
    align-items: center;
    width: auto;
  }
`;

const SummaryStatsItem = styled.div`
  display: inline-flex;
  gap: 0.3em;
  align-items: center;
  justify-content: center;
  white-space: nowrap;
  border: 1px solid transparent;
  &.emphasized {
    color: #0c0d0e;
  }
`;

const SummaryStatsItemNumber = styled.span`
  font-weight: 500;
`;

const SummaryStatsItemUnit = styled.span``;

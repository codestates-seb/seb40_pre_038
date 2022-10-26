import { useState, useEffect } from 'react';
import styled from 'styled-components';

const SummaryStats = ({
  votes = 0,
  answers = 0,
  hasAccepted = false,
  views = 10000,
}) => {
  const [viewsClassName, setViewsClassName] = useState('');
  const [viewsText, setViewsText] = useState(views);

  useEffect(() => {
    if (views < 1000) {
      setViewsClassName('');
      setViewsText(views);
      return;
    }

    const k = Math.floor(views / 1000);
    if (views < 10000) {
      setViewsClassName('is-warm');
      setViewsText(`${k}k`);
      return;
    }

    setViewsClassName('is-hot');
    if (views < 1000000) {
      setViewsText(`${k}k`);
      return;
    }

    const m = Math.floor(k / 1000);
    if (views < 1000000000) {
      setViewsText(`${m}m`);
      return;
    }

    const b = Math.floor(m / 1000);
    setViewsText(`${b}b`);
  }, [views]);

  return (
    <SummaryStatsWrapper>
      <SummaryStatsItem className="emphasized" title={`Score of ${votes}`}>
        <SummaryStatsItemNumber>{votes}</SummaryStatsItemNumber>
        <SummaryStatsItemUnit>votes</SummaryStatsItemUnit>
      </SummaryStatsItem>
      <SummaryStatsItem
        className={
          answers <= 0
            ? ''
            : hasAccepted
            ? 'has-accepted-answer'
            : 'has-answers'
        }
        title={`${answers} answers`}
      >
        {answers <= 0 ? null : (
          <HasAcceptedAnswerSvg aria-hidden="true" viewBox="0 0 14 14">
            <path d="M13 3.41 11.59 2 5 8.59 2.41 6 1 7.41l4 4 8-8Z"></path>
          </HasAcceptedAnswerSvg>
        )}
        <SummaryStatsItemNumber>{answers}</SummaryStatsItemNumber>
        <SummaryStatsItemUnit>answers</SummaryStatsItemUnit>
      </SummaryStatsItem>
      <SummaryStatsItem className={viewsClassName} title={`${views} views`}>
        <SummaryStatsItemNumber>{viewsText}</SummaryStatsItemNumber>
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
  &.has-answers {
    color: #2f6f44;
  }
  &.has-accepted-answer {
    color: #fff;
    background-color: #2f6f44;
    border-color: #2f6f44;
  }
  &.has-answers,
  &.has-accepted-answer {
    border: 1px solid #2f6f44;
    border-radius: 3px;
    padding: 2px 4px;
  }
  &.is-warm {
    color: #83690b;
  }
  &.is-hot {
    color: #a7510c;
  }
`;

const SummaryStatsItemNumber = styled.span`
  font-weight: 500;
`;

const SummaryStatsItemUnit = styled.span``;

const HasAcceptedAnswerSvg = styled.svg`
  vertical-align: bottom;
  width: 14px;
  height: 14px;
  overflow: hidden;
  path {
    fill: currentColor;
  }
`;

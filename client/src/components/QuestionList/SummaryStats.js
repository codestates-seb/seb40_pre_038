import { useState, useEffect } from 'react';
import styled from 'styled-components';

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
  }
  &.has-answers,
  &.has-bounty,
  &.is-deleted,
  &.is-published,
  &.is-draft,
  &.is-review,
  &.is-closed,
  &.is-archived,
  &.is-pinned,
  &.has-accepted-answer {
    border-radius: 3px;
    padding: 2px 4px;
  }
  &.has-bounty {
    color: #fff;
    background-color: #0074cc;
  }
  &.is-warm {
    color: #83690b;
  }
  &.is-hot {
    color: #a7510c;
  }
  &.is-supernova {
    color: #922024;
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

const SummaryStats = ({ question }) => {
  const {
    vote,
    answerCount,
    accepted,
    view,
    hasBounty = false,
    bounty = 0,
  } = question;
  const [viewsClassName, setViewsClassName] = useState('');
  const [viewsText, setViewsText] = useState(view);

  useEffect(() => {
    setViewsStats();
  }, [view]);

  const setViewsStats = () => {
    if (view < 1000) {
      setViewsClassName('');
      setViewsText(view);
      return;
    }

    const k = Math.floor(view / 1000);
    if (view < 10000) {
      setViewsClassName('is-warm');
      setViewsText(`${k}k`);
      return;
    }

    if (view < 1000000) {
      setViewsClassName('is-hot');
      setViewsText(`${k}k`);
      return;
    }

    setViewsClassName('is-supernova');
    const m = Math.floor(k / 1000);
    if (view < 1000000000) {
      setViewsText(`${m}m`);
      return;
    }

    const b = Math.floor(m / 1000);
    setViewsText(`${b}b`);
  };

  return (
    <SummaryStatsWrapper>
      <SummaryStatsItem className="emphasized" title={`Score of ${vote}`}>
        <SummaryStatsItemNumber>{vote}</SummaryStatsItemNumber>
        <SummaryStatsItemUnit>votes</SummaryStatsItemUnit>
      </SummaryStatsItem>
      <SummaryStatsItem
        className={
          answerCount <= 0
            ? ''
            : accepted
            ? 'has-accepted-answer'
            : 'has-answers'
        }
        title={`${answerCount} answers`}
      >
        {answerCount > 0 && accepted ? (
          <HasAcceptedAnswerSvg aria-hidden="true" viewBox="0 0 14 14">
            <path d="M13 3.41 11.59 2 5 8.59 2.41 6 1 7.41l4 4 8-8Z"></path>
          </HasAcceptedAnswerSvg>
        ) : null}
        <SummaryStatsItemNumber>{answerCount}</SummaryStatsItemNumber>
        <SummaryStatsItemUnit>answers</SummaryStatsItemUnit>
      </SummaryStatsItem>
      <SummaryStatsItem className={viewsClassName} title={`${view} views`}>
        <SummaryStatsItemNumber>{viewsText}</SummaryStatsItemNumber>
        <SummaryStatsItemUnit>views</SummaryStatsItemUnit>
      </SummaryStatsItem>
      {hasBounty ? (
        <SummaryStatsItem
          className="has-bounty"
          title="this question has an open bounty worth 50 reputation"
        >
          +{bounty}
        </SummaryStatsItem>
      ) : null}
    </SummaryStatsWrapper>
  );
};

export default SummaryStats;

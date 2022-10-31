import styled from 'styled-components';

export const Bluebutton = styled.button`
  background: #0995ff;
  color: #ffffff;
  font-weight: bold;
  border: 1px solid #0995ff;
  border-radius: 4px;
  box-shadow: inset 0 1px 0 0 #ffffff;
  width: ${(props) => props.width || '100px'};
  height: ${(props) => props.height || '40px'};
  font-size: ${(props) => props.fontSize || '14px'};
  font-weight: ${(props) => props.fontWeight || '700'};
  :hover {
    background: #0063bf;
  }
`;

const SkyblueButton = styled.button`
  background: #e1ecf4;
  color: #3a739d;
  border-radius: 4px;
  border: ${(props) => props.border || '1px solid #3a739d'};
  width: ${(props) => props.width || '100px'};
  height: ${(props) => props.height || '40px'};
  font-size: ${(props) => props.fontSize || '14px'};
  font-weight: ${(props) => props.fontWeight || '700'};
  :hover {
    background: #b3d3ea;
  }
`;

export function ButtonBlue({
  children,
  border,
  width,
  height,
  fontSize,
  fontWeight,
}) {
  return (
    <Bluebutton
      border={border}
      width={width}
      height={height}
      fontSize={fontSize}
      fontWeight={fontWeight}
    >
      {children}
    </Bluebutton>
  );
}

export function ButtonSblue({ children, width, height, fontSize, fontWeight }) {
  return (
    <SkyblueButton
      width={width}
      height={height}
      fontSize={fontSize}
      fontWeight={fontWeight}
    >
      {children}
    </SkyblueButton>
  );
}

/**
 * Button
 * 미완성 컴포넌트 입니다.
 */
const ButtonComponent = styled.button`
  position: relative;
  display: inline-block;
  padding: 0.8em;
  color: #0074cc;
  border: 1px solid transparent;
  border-radius: 3px;
  background-color: transparent;
  outline: none;
  font-family: inherit;
  font-size: 13px;
  font-weight: normal;
  line-height: calc((13 + 2) / 13);
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  user-select: none;
  white-space: ${(props) =>
    props.whiteSpace === 'nowrap' ? 'nowrap !important' : 'normal'};

  &.primary {
    color: #ffffff;
    background-color: #0a95ff;
    box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
  }

  :hover,
  :focus,
  :active {
    color: #ffffff;
    background-color: #0074cc;
  }
  :active {
    background-color: #0063bf;
    box-shadow: none;
  }
`;

export const Button = ({ children, type, whiteSpace, onClick }) => {
  const handleClickButton = (e) => {
    onClick(e);
  };

  return (
    <ButtonComponent
      className={type}
      whiteSpace={whiteSpace}
      onClick={handleClickButton}
    >
      {children}
    </ButtonComponent>
  );
};

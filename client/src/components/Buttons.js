import { useState, useEffect } from 'react';
import styled from 'styled-components';

const Bluebutton = styled.button`
  background: #0995ff;
  color: #ffffff;
  font-weight: bold;
  border: 1px solid #0995ff;
  border-radius: 4px;
  box-shadow: inset 0 1px 0 0 #ffffff;
  width: ${(props) => props.width || '100px'};
  height: ${(props) => props.height || '40px'};
  font-size: ${(props) => props.fontSize || '14px'};
  :hover {
    background: #0063bf;
  }
`;

const SkyblueButton = styled.button`
  background: #e1ecf4;
  color: #3a739d;
  font-weight: bold;
  border-radius: 4px;
  border: ${(props) => props.border || '1px solid #3a739d'};
  width: ${(props) => props.width || '100px'};
  height: ${(props) => props.height || '40px'};
  font-size: ${(props) => props.fontSize || '14px'};
  :hover {
    background: #b3d3ea;
  }
`;

function ButtonBlue({ children, border, width, height, fontSize }) {
  return (
    <Bluebutton
      border={border}
      width={width}
      height={height}
      fontSize={fontSize}
    >
      {children}
    </Bluebutton>
  );
}

function ButtonSblue({ children, width, height, fontSize }) {
  return (
    <SkyblueButton width={width} height={height} fontSize={fontSize}>
      {children}
    </SkyblueButton>
  );
}

function Button({ children, type }) {
  const [color, setColor] = useState('');
  const [backgroundColor, setbackgroundColor] = useState('');

  useEffect(() => {
    switch (type) {
      case 'primary':
        setColor('#ffffff');
        setbackgroundColor('#0A95FF');
        break;
      default:
        setColor('#0074CC');
        setbackgroundColor('transparent');
        break;
    }
  }, [type]);

  return (
    <ButtonComponent color={color} backgroundColor={backgroundColor}>
      {children}
    </ButtonComponent>
  );
}

const ButtonComponent = styled.button`
  position: relative;
  display: inline-block;
  padding: 0.8em;
  color: ${(props) => props.color};
  border: 1px solid transparent;
  border-radius: 3px;
  background-color: ${(props) => props.backgroundColor};
  outline: none;
  font-family: inherit;
  font-size: 13px;
  font-weight: normal;
  line-height: calc((13 + 2) / 13);
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  user-select: none;
  box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);

  white-space: nowrap !important;

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

export { ButtonBlue, ButtonSblue, Button };

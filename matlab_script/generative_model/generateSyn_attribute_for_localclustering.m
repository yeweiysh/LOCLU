clear;
clc;

for i=1:1
    i
    filename=['C:\Users\Desktop\local clustering\synthetic graph data\runtime\varying attributes\' num2str(i) '\community.dat'];
    label= importdata(filename);
    
    
    n=size(label,1);
    
    for j=[20,40,60,80,100]
        
        releDim=0.2*j;
        data1=zeros(n,releDim);
        irreleDim=j-releDim;
        numClus=length(unique(label(:,2)));
        
        for k=1:numClus
            index=find(label(:,2)==k);
            num=length(index);
            re=0.001*randn(num,releDim);
            re=addMean(re);
            
            for i1=1:num
                data1(index(i1),:)=re(i1,:);
            end
        end
        
        
        data2=zeros(n,irreleDim);
        for j1=1:irreleDim
            pp=randperm(n);
            label1=label(pp,:);
            for k=1:numClus
                index=find(label1(:,2)==k);
                num=length(index);
                
                ir=randn(num,1);
                ir=addMean2(ir);
                data2(index,j1)=ir;
            end
        end
        data=[data1,data2];
        
        filename=['C:\Users\Desktop\local clustering\synthetic graph data\runtime\varying attributes\' num2str(i) '\' num2str(j) '\data.txt'];
        fileID = fopen(filename,'w');
        [row,col]=size(data);
        for ii=1:row
            for jj=1:col
                fprintf(fileID, '%f ', data(ii,jj));
            end
            fprintf(fileID, '\n');
        end
        fclose(fileID);
        data=[];
    end
end
